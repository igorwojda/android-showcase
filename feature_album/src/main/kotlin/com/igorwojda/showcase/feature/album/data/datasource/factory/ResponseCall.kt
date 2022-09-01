package com.example.data.network.retrofit.factory

import com.igorwojda.showcase.feature.album.data.datasource.factory.status.Result
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ResponseCall<T> constructor(
    private val callDelegate: Call<T>,
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) = callDelegate.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            response.body()?.let {
                when (response.code()) {
                    in 200..208 -> {
                        callback.onResponse(this@ResponseCall, Response.success(Result.Success(it)))
                    }
                    in 400..409 -> {
                        callback.onResponse(this@ResponseCall,
                            Response.success(Result.Error(response.code(), response.message())))
                    }
                }
            } ?: callback.onResponse(this@ResponseCall, Response.success(Result.Error(123, "message")))
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            callback.onResponse(this@ResponseCall, Response.success(Result.Exception(t)))
            call.cancel()
        }
    })

    override fun clone(): Call<Result<T>> = ResponseCall(callDelegate.clone())

    override fun execute(): Response<Result<T>> =
        throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = callDelegate.isExecuted

    override fun cancel() = callDelegate.cancel()

    override fun isCanceled(): Boolean = callDelegate.isCanceled

    override fun request(): Request = callDelegate.request()

    override fun timeout(): Timeout = callDelegate.timeout()
}
