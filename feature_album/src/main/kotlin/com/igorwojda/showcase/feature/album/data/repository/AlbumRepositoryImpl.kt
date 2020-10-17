package com.igorwojda.showcase.feature.album.data.repository

import com.igorwojda.showcase.feature.album.data.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.model.toEntity
import com.igorwojda.showcase.feature.album.data.retrofit.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.room.AlbumDatabase
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService,
    private val albumDatabase: AlbumDatabase
) : AlbumRepository {

    override suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?): AlbumDomainModel? {
        return try {
            albumRetrofitService.getAlbumInfoAsync(artistName, albumName, mbId)
                ?.album
                ?.toDomainModel()
        } catch (e: java.net.UnknownHostException) {
            albumDatabase.albums()
                .getAlbum(artistName, albumName, mbId).toDomainModel()
        }
    }

    override suspend fun searchAlbum(phrase: String): List<AlbumDomainModel> {
        return try {
            val albumsListResponse = albumRetrofitService.searchAlbumAsync(phrase)
                .results
                .albumMatches
                .album

            albumsListResponse
                .map { it.toEntity() }
                .let { albumDatabase.albums().insertAlbums(it) }

            albumsListResponse
                .map { it.toDomainModel() }
        } catch (e: java.net.UnknownHostException) {
            albumDatabase.albums().getAll()
                .map { it.toDomainModel() }
        }
    }

}
