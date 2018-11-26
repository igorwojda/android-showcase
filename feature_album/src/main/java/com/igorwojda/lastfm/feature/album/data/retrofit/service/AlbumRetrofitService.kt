package com.igorwojda.lastfm.feature.album.data.retrofit.service

import com.igorwojda.lastfm.feature.album.data.retrofit.response.GetAlbumInfoResponse
import com.igorwojda.lastfm.feature.album.data.retrofit.response.SearchAlbumResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.POST
import retrofit2.http.Query

// Api docs: https://www.last.fm/api/intro
interface AlbumRetrofitService {
    @POST("./?method=album.search")
    fun searchAlbum(
        @Query("album") phrase: String,
        @Query("format") format: String = "json",
        @Query("api_key") apiKey: String = "70696db59158cb100370ad30a7a705c1"
    ): Deferred<SearchAlbumResponse>

    @POST("./?method=album.getInfo")
    fun getAlbumInfo(
        @Query("artist") artistName: String,
        @Query("album") albumName: String,
        @Query("format") format: String = "json",
        @Query("api_key") apiKey: String = "70696db59158cb100370ad30a7a705c1"
    ): Deferred<GetAlbumInfoResponse>
}
