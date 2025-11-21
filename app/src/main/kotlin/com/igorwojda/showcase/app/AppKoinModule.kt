package com.igorwojda.showcase.app

import android.annotation.SuppressLint
import com.igorwojda.showcase.app.data.api.AuthenticationInterceptor
import com.igorwojda.showcase.app.data.api.UserAgentInterceptor
import com.igorwojda.showcase.feature.base.data.retrofit.ApiResultAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import timber.log.Timber

val appModule =
    module {

        single { AuthenticationInterceptor(BuildConfig.GRADLE_API_TOKEN) }

        singleOf(::UserAgentInterceptor)

        single {
            HttpLoggingInterceptor { message ->
                Timber.d("Http: $message")
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        /*
         * OkHttp logging interceptor with custom Timber logger.
         *
         * By default, HttpLoggingInterceptor uses the calling class name as the log tag which clutters Logcat and makes filtering harder.
         *
         * This custom configuration ensures:
         * - All HTTP logs are tagged consistently as `"Network"`.
         * - Logs are printed through Timber (instead of Android's `Log`).
         * - Logging level is set to BODY to include headers and payloads.
         */
        single {
            HttpLoggingInterceptor { message ->
                Timber.tag("Network").d(message)
            }.apply {
                /*
                Use BODY logging only in debug builds.
                Even if Timber.DebugTree() is planted only in debug, the interceptor still
                reads/constructs request/response bodies when level = BODY.
                This adds unnecessary overhead and may leak sensitive data if any logger
                is active in production. Setting NONE in release avoids both risks.
                 */
                level =
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
            }
        }

        single {
            OkHttpClient
                .Builder()
                .apply {
                    if (BuildConfig.DEBUG) {
                        addInterceptor(get<HttpLoggingInterceptor>())
                        // Disable SSL validation for debugging with proxy tools (Proxyman, Charles, etc.)
                        disableAllSslValidation()
                    }
                    addInterceptor(get<AuthenticationInterceptor>())
                    addInterceptor(get<UserAgentInterceptor>())
                }.build()
        }

        single {
            val contentType = "application/json".toMediaType()

            val json =
                kotlinx.serialization.json.Json {
                    // By default Kotlin serialization will serialize all of the keys present in JSON object and throw an
                    // exception if given key is not present in the Kotlin class. This flag allows to ignore JSON fields
                    ignoreUnknownKeys = true
                }

            @OptIn(ExperimentalSerializationApi::class)
            Retrofit
                .Builder()
                .baseUrl(BuildConfig.GRADLE_API_BASE_URL)
                .client(get())
                .addConverterFactory(json.asConverterFactory(contentType))
                .addCallAdapterFactory(ApiResultAdapterFactory())
                .build()
        }
    }

/**
 * Disable SSL/TLS validation on this OkHttpClient builder.
 * Installs a permissive X509TrustManager and HostnameVerifier. Use ONLY for local
 * debugging (e.g., proxy tools) and never in production — this completely disables SSL.
 */
@SuppressLint("CustomX509TrustManager", "TrustAllX509TrustManager")
private fun OkHttpClient.Builder.disableAllSslValidation() =
    apply {
        val naiveTrustManager =
            object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

                override fun checkClientTrusted(
                    certs: Array<X509Certificate>,
                    authType: String,
                ) = Unit

                override fun checkServerTrusted(
                    certs: Array<X509Certificate>,
                    authType: String,
                ) = Unit
            }

        val insecureSocketFactory =
            SSLContext
                .getInstance("TLSv1.2")
                .apply { init(null, arrayOf(naiveTrustManager), SecureRandom()) }
                .socketFactory

        sslSocketFactory(insecureSocketFactory, naiveTrustManager)
        hostnameVerifier { _, _ -> true }
    }
