package com.igorwojda.showcase.feature.album.data.repository

import com.igorwojda.showcase.base.common.Result
import com.igorwojda.showcase.feature.album.data.datasource.api.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.toEntity
import com.igorwojda.showcase.feature.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.datasource.apiresponse.ApiResult
import com.igorwojda.showcase.feature.album.data.datasource.database.AlbumDao
import com.igorwojda.showcase.feature.album.data.datasource.database.model.toDomainModel
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import java.net.UnknownHostException

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService,
    private val albumDao: AlbumDao,
) : AlbumRepository {

    override suspend fun searchAlbum(phrase: String): Result<List<Album>> =
        when (val apiResult = albumRetrofitService.searchAlbumAsync(phrase)) {
            is ApiResult.Success -> {
                val albums = apiResult
                    .data
                    .results
                    .albumMatches
                    .album
                    .map { it.toEntity() }
                    .also { albumDao.insertAlbums(it) }
                    .map { it.toDomainModel() }

                Result.Success(albums)
            }
            is ApiResult.Error -> {
                Result.Failure()
            }
            is ApiResult.Exception -> {
                val albums = albumDao
                    .getAll()
                    .map { it.toDomainModel() }

                Result.Success(albums)
            }
        }

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
