package com.example.starter.data.serivce

import retrofit2.http.GET
import retrofit2.http.Query

interface CustomService {

    @GET("results")
    suspend fun search(@Query("search_query") query: String): String
}
