package com.igorwojda.lastfm.feature.album.data.retrofit

import com.igorwojda.lastfm.feature.album.data.repository.AlbumSearchResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.POST
import retrofit2.http.Query

// Api docs: https://www.last.fm/api/intro
interface AlbumRetrofitService {
    @POST("./")
    fun searchAlbum(
        @Query("album") phrase: String,
        @Query("format") format: String = "json",
        @Query("method") method: String = "album.search",
        @Query("api_key") apiKey: String = "70696db59158cb100370ad30a7a705c1"
    ): Deferred<AlbumSearchResponse>
}
