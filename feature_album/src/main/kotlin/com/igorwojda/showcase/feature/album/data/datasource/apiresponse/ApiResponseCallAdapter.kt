package com.example.data.network.retrofit.factory

import com.igorwojda.showcase.feature.album.data.datasource.apiresponse.ApiResult
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ApiResponseCallAdapter<T>(
    private val successType: Type,
) : CallAdapter<T, Call<ApiResult<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<ApiResult<T>> = ApiResponseCall(call)
}
