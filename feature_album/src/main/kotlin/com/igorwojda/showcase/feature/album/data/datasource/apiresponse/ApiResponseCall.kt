package com.example.data.network.retrofit.factory

import com.igorwojda.showcase.feature.album.data.datasource.apiresponse.ApiResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ApiResponseCall<T> constructor(
    private val callDelegate: Call<T>,
) : Call<ApiResult<T>> {

    override fun enqueue(callback: Callback<ApiResult<T>>) = callDelegate.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let {
                when (response.code()) {
                    in 200..208 -> {
                        callback.onResponse(this@ApiResponseCall, Response.success(ApiResult.Success(it)))
                    }
                    in 400..409 -> {
                        callback.onResponse(this@ApiResponseCall,
                            Response.success(ApiResult.Error(response.code(), response.message())))
                    }
                }
            } ?: callback.onResponse(this@ApiResponseCall, Response.success(ApiResult.Error(123, "message")))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            callback.onResponse(this@ApiResponseCall, Response.success(ApiResult.Exception(t)))
            call.cancel()
        }
    })

    override fun clone(): Call<ApiResult<T>> = ApiResponseCall(callDelegate.clone())

    override fun execute(): Response<ApiResult<T>> =
        throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = callDelegate.isExecuted

    override fun cancel() = callDelegate.cancel()

    override fun isCanceled(): Boolean = callDelegate.isCanceled

    override fun request(): Request = callDelegate.request()

    override fun timeout(): Timeout = callDelegate.timeout()
}
