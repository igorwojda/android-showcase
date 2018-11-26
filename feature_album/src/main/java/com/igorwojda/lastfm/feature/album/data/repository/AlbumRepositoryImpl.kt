package com.igorwojda.lastfm.feature.album.data.repository

import com.igorwojda.lastfm.feature.album.data.model.searchalbum.toDomainModel
import com.igorwojda.lastfm.feature.album.data.retrofit.AlbumRetrofitService
import com.igorwojda.lastfm.feature.album.domain.model.searchalbum.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository

class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService
) : AlbumRepository {
    override suspend fun getAlbum(albumName: String, artistName: String): AlbumDomainModel? {
//        albumRetrofitService.
        
//        val (_, _, result) = Fuel.get("/albums").awaitObjectResponse<List<AlbumNetworkModel>>(gsonDeserializerOf())
//
//        return result.getOrElse(listOf())
//            .map { it.toDomainModel() }

        return null
    }

    override suspend fun searchAlbum(phrase: String) =
        albumRetrofitService.searchAlbum(phrase)
            .await()
            .results
            .albumMatches
            .album
            .map { it.toDomainModel() }
}
