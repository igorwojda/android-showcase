package com.igorwojda.lastfm.feature.album.data.repository

import com.igorwojda.lastfm.feature.album.data.retrofit.AlbumRetrofitService
import com.igorwojda.lastfm.feature.album.domain.model.OldAlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository

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

data class AlbumSearchResult(
    val albummatches: List<Album>
)

data class Album(
    val name: String,
    val artist: String,
    val id: String,
    val url: String
)
