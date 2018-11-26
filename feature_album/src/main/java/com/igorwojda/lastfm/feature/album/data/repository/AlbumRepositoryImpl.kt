package com.igorwojda.lastfm.feature.album.data.repository

import com.igorwojda.lastfm.feature.album.data.retrofit.AlbumRetrofitService
import com.igorwojda.lastfm.feature.album.domain.model.OldAlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository
import com.squareup.moshi.Json

class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService
) : AlbumRepository {
    override suspend fun getAlbum(id: String): OldAlbumDomainModel? {
//        val (_, _, result) = Fuel.get("/albums").awaitObjectResponse<List<AlbumNetworkModel>>(gsonDeserializerOf())
//
//        return result.getOrElse(listOf())
//            .map { it.toDomainModel() }

        return null
    }

    override suspend fun searchAlbum(phrase: String): List<OldAlbumDomainModel> {
        val result = albumRetrofitService.searchAlbum(phrase)

//        val (_, _, result) = Fuel.get("/albums/$id").awaitObjectResponse<AlbumNetworkModel>(gsonDeserializerOf())
//
//        var album: AlbumNetworkModel? = null
//        result.fold(success = { album = it }, failure = {})
//
//        return album?.toDomainModel()

        return listOf()
    }
}

data class AlbumSearchResponse(
    val results: AlbumSearchResult
)

data class AlbumSearchResult(
    @field:Json(name = "albummatches") val albumMatches: AlbumList?
)

data class AlbumList(
    val album: List<Album>
)

data class Album(
    val name: String,
    val artist: String,
    val url: String,
    @field:Json(name = "mdId") val mbid: String,
    @field:Json(name = "image") val images: List<LastFmImage> = listOf()
)

data class LastFmImage(
    @field:Json(name = "#text") val url: String,
    val size: LastFmImageSize
)

enum class LastFmImageSize {
    @field:Json(name = "medium")
    MEDIUM,
    @field:Json(name = "small")
    SMALL,
    @field:Json(name = "large")
    LARGE,
    @field:Json(name = "extralarge")
    EXTRA_LARGE
}
