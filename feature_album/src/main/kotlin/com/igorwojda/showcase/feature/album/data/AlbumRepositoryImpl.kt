package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.database.AlbumDao
import com.igorwojda.showcase.feature.album.data.network.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.network.model.toEntity
import com.igorwojda.showcase.feature.album.data.network.retrofit.service.AlbumRetrofitService
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
            val searchAlbumResponse = albumRetrofitService.searchAlbumAsync(phrase)
            val albumList = searchAlbumResponse.results.albumMatchesNetwork.album

            albumList
                .map { it.toEntity() }
                .let { albumDao.insertAlbums(it) }

            albumList.map { it.toDomainModel() }

        } catch (e: java.net.UnknownHostException) {
            albumDao.getAll()
                .map { it.toDomainModel() }
        }
    }
}
