package com.igorwojda.showcase.feature.album.data.repository

import com.igorwojda.showcase.feature.album.data.datasource.api.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.toEntity
import com.igorwojda.showcase.feature.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.datasource.database.AlbumDao
import com.igorwojda.showcase.feature.album.data.datasource.database.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.datasource.factory.status.Result
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import java.net.UnknownHostException

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService,
    private val albumDao: AlbumDao,
) : AlbumRepository {

    override suspend fun searchAlbum(phrase: String): List<Album> =
        when (val result = albumRetrofitService.searchAlbumAsync(phrase)) {
//            is NetworkResult.NetworkError -> {
//                Timber.d("NetworkResult.NetworkError")
//                emptyList()
//            }
//            is NetworkResult.NetworkException -> {
//                Timber.d("NetworkResult.NetworkException")
//                emptyList()
//            }
//            is NetworkResult.NetworkSuccess -> {
//                Timber.d("NetworkResult.NetworkSuccess")
//                emptyList()
//            }
            is Result.Success -> {
                result
                    .data
                    .results
                    .albumMatches
                    .album
                    .map { it.toEntity() }
                    .map { it.toDomainModel() }
            }
            is Result.Error -> emptyList()
            is Result.Exception -> emptyList()
        }

//        return try {
//            val searchAlbumResponse = albumRetrofitService.searchAlbumAsync(phrase)
//            val albumList = searchAlbumResponse.results.albumMatches.album
//
//            albumList
//                .map { it.toEntity() }
//                .let { albumDao.insertAlbum(it) }
//
//            albumList.map { it.toDomainModel() }
//        } catch (e: UnknownHostException) {
//            albumDao.getAll()
//                .map { it.toDomainModel() }
//        }

    override suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?): Album? {
        return try {
            albumRetrofitService.getAlbumInfoAsync(artistName, albumName, mbId)
                ?.album
                ?.toDomainModel()
        } catch (e: UnknownHostException) {
            albumDao.getAlbum(artistName, albumName, mbId).toDomainModel()
        }
    }
}
