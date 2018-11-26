package com.igorwojda.lastfm.feature.album.data.repository

import com.igorwojda.lastfm.feature.album.data.model.toDomainModel
import com.igorwojda.lastfm.feature.album.data.retrofit.service.AlbumRetrofitService
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository

class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService
) : AlbumRepository {
    override suspend fun getAlbumInfo(artistName: String, albumName: String) =
        albumRetrofitService.getAlbumInfo(artistName, albumName)
            .await()
            .album
            .toDomainModel()

    override suspend fun searchAlbum(phrase: String) =
        albumRetrofitService.searchAlbum(phrase)
            .await()
            .results
            .albumMatches
            .album
            .map { it.toDomainModel() }
}
