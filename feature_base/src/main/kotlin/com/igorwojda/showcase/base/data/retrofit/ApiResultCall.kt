package com.igorwojda.showcase.base.data.retrofit

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ApiResultCall<T> constructor(
    private val callDelegate: Call<T>,
) : Call<ApiResult<T>> {

    @Suppress("detekt.MagicNumber")
    override fun enqueue(callback: Callback<ApiResult<T>>) = callDelegate.enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let {
                    when (response.code()) {
                        in 200..208 -> {
                            callback.onResponse(this@ApiResultCall, Response.success(ApiResult.Success(it)))
                        }
                        in 400..409 -> {
                            callback.onResponse(
                                this@ApiResultCall,
                                Response.success(ApiResult.Error(response.code(), response.message())),
                            )
                        }
                    }
                } ?: callback.onResponse(this@ApiResultCall, Response.success(ApiResult.Error(123, "message")))
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(this@ApiResultCall, Response.success(ApiResult.Exception(throwable)))
                call.cancel()
            }
        },
    )

    override fun clone(): Call<ApiResult<T>> = ApiResultCall(callDelegate.clone())

    override fun execute(): Response<ApiResult<T>> =
        throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = callDelegate.isExecuted

    override fun cancel() = callDelegate.cancel()

    override fun isCanceled(): Boolean = callDelegate.isCanceled

    override fun request(): Request = callDelegate.request()

    override fun timeout(): Timeout = callDelegate.timeout()
}
