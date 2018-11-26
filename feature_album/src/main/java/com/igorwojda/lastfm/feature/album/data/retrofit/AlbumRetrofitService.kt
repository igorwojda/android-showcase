package com.igorwojda.lastfm.feature.album.data.retrofit

import com.igorwojda.lastfm.feature.album.data.repository.AlbumSearchResult
import kotlinx.coroutines.Deferred
import retrofit2.http.POST
import retrofit2.http.Query

// Api docs: https://www.last.fm/api/intro
interface AlbumRetrofitService {
    @POST("?method=album.search&format=json&api_key=70696db59158cb100370ad30a7a705c1")
    fun searchAlbum(@Query("album") phrase: String): Deferred<AlbumSearchResult>
}
