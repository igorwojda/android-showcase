package com.igorwojda.showcase.feature.album.data.repository

import com.igorwojda.showcase.feature.album.data.model.network.toDomainModel
import com.igorwojda.showcase.feature.album.data.model.network.toEntity
import com.igorwojda.showcase.feature.album.data.retrofit.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.room.AlbumDao
import com.igorwojda.showcase.feature.album.data.model.database.toDomainModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService,
    private val albumDao: AlbumDao
) : AlbumRepository {

    override suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?): AlbumDomainModel? {
        return try {
            albumRetrofitService.getAlbumInfoAsync(artistName, albumName, mbId)
                ?.album
                ?.toDomainModel()
        } catch (e: java.net.UnknownHostException) {
            albumDao.getAlbum(artistName, albumName, mbId).toDomainModel()
        }
    }

    override suspend fun searchAlbum(phrase: String): List<AlbumDomainModel> {
        return try {
            val albumsListResponse =
                albumRetrofitService.searchAlbumAsync(phrase)
                    .results
                    .albumMatchesNetwork
                    .album

            albumsListResponse
                .map { it.toEntity() }
                .let { albumDao.insertAlbums(it) }

            albumsListResponse
                .map { it.toDomainModel() }
        } catch (e: java.net.UnknownHostException) {
            albumDao.getAll()
                .map { it.toDomainModel() }
        }
    }
}
