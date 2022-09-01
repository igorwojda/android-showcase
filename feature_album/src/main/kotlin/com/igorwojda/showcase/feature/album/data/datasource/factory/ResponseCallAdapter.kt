package com.example.data.network.retrofit.factory

import com.igorwojda.showcase.feature.album.data.datasource.factory.status.Result
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ResponseCallAdapter<T>(
    private val successType: Type,
) : CallAdapter<T, Call<Result<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<Result<T>> = ResponseCall(call)
}
