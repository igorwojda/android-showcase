package com.igorwojda.showcase.feature.album.data.repository

import com.igorwojda.showcase.feature.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.datasource.database.AlbumDao
import com.igorwojda.showcase.feature.album.data.mapper.AlbumMapper
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import com.igorwojda.showcase.feature.base.data.retrofit.ApiResult
import com.igorwojda.showcase.feature.base.domain.result.Result
import timber.log.Timber

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService,
    private val albumDao: AlbumDao,
    private val albumMapper: AlbumMapper,
) : AlbumRepository {
    override suspend fun searchAlbum(phrase: String?): Result<List<Album>> =
        when (val apiResult = albumRetrofitService.searchAlbumAsync(phrase)) {
            is ApiResult.Success -> {
                val albums =
                    apiResult
                        .data
                        .results
                        .albumMatches
                        .album
                        .also { albumsApiModels ->
                            val albumsRoomModels = albumsApiModels.map { albumMapper.apiToRoom(it) }
                            albumDao.insertAlbums(albumsRoomModels)
                        }.map { albumMapper.apiToDomain(it) }

                Result.Success(albums)
            }
            is ApiResult.Error -> {
                Result.Failure()
            }
            is ApiResult.Exception -> {
                Timber.e(apiResult.throwable)

                val albums =
                    albumDao
                        .getAll()
                        .map { albumMapper.roomToDomain(it) }

                Result.Success(albums)
            }
        }

    override suspend fun getAlbumInfo(
        artistName: String,
        albumName: String,
        mbId: String?,
    ): Result<Album> =
        when (val apiResult = albumRetrofitService.getAlbumInfoAsync(artistName, albumName, mbId)) {
            is ApiResult.Success -> {
                val album =
                    apiResult
                        .data
                        .album
                        .let { albumMapper.apiToDomain(it) }

                Result.Success(album)
            }
            is ApiResult.Error -> {
                Result.Failure()
            }
            is ApiResult.Exception -> {
                Timber.e(apiResult.throwable)

                val album =
                    albumDao
                        .getAlbum(artistName, albumName, mbId)
                        .let { albumMapper.roomToDomain(it) }

                Result.Success(album)
            }
        }
}
