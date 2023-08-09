package com.igorwojda.showcase.album.data.repository

import com.igorwojda.showcase.album.data.datasource.api.model.toDomainModel
import com.igorwojda.showcase.album.data.datasource.api.model.toEntityModel
import com.igorwojda.showcase.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.album.data.datasource.database.AlbumDao
import com.igorwojda.showcase.album.data.datasource.database.model.toDomainModel
import com.igorwojda.showcase.album.domain.model.Album
import com.igorwojda.showcase.album.domain.repository.AlbumRepository
import com.igorwojda.showcase.base.data.retrofit.ApiResult
import com.igorwojda.showcase.base.domain.result.Result
import timber.log.Timber

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService,
    private val albumDao: AlbumDao,
) : AlbumRepository {

    override suspend fun searchAlbum(phrase: String?): Result<List<Album>> =
        when (val apiResult = albumRetrofitService.searchAlbumAsync(phrase)) {
            is ApiResult.Success -> {
                val albums = apiResult
                    .data
                    .results
                    .albumMatches
                    .album
                    .also { albumsApiModels ->
                        val albumsEntityModels = albumsApiModels.map { it.toEntityModel() }
                        albumDao.insertAlbums(albumsEntityModels)
                    }
                    .map { it.toDomainModel() }

                Result.Success(albums)
            }
            is ApiResult.Error -> {
                Result.Failure()
            }
            is ApiResult.Exception -> {
                Timber.e(apiResult.throwable)

                val albums = albumDao
                    .getAll()
                    .map { it.toDomainModel() }

                Result.Success(albums)
            }
        }

    override suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?): Result<Album> =
        when (val apiResult = albumRetrofitService.getAlbumInfoAsync(artistName, albumName, mbId)) {
            is ApiResult.Success -> {
                val album = apiResult
                    .data
                    .album
                    .toDomainModel()

                Result.Success(album)
            }
            is ApiResult.Error -> {
                Result.Failure()
            }
            is ApiResult.Exception -> {
                Timber.e(apiResult.throwable)

                val album = albumDao
                    .getAlbum(artistName, albumName, mbId)
                    .toDomainModel()

                Result.Success(album)
            }
        }
}
