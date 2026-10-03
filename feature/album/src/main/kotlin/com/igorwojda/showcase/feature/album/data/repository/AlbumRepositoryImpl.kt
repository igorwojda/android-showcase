package com.igorwojda.showcase.feature.album.data.repository

import com.igorwojda.showcase.feature.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.datasource.database.AlbumDao
import com.igorwojda.showcase.feature.album.data.mapper.AlbumMapper
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import com.igorwojda.showcase.feature.base.data.error.dataResult
import com.igorwojda.showcase.feature.base.data.error.httpError
import com.igorwojda.showcase.feature.base.data.error.toAppError
import com.igorwojda.showcase.feature.base.data.retrofit.ApiResult
import com.igorwojda.showcase.feature.base.domain.error.AppError
import com.igorwojda.showcase.feature.base.domain.result.Result

internal class AlbumRepositoryImpl(
    private val albumRetrofitService: AlbumRetrofitService,
    private val albumDao: AlbumDao,
    private val albumMapper: AlbumMapper,
) : AlbumRepository {
    override suspend fun searchAlbum(phrase: String?): Result<List<Album>> =
        dataResult {
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
                    Result.Failure(httpError(apiResult.code))
                }

                is ApiResult.Exception -> {
                    val exception = apiResult.throwable
                    if (exception !is Exception) throw exception
                    val error = exception.toAppError()
                    if (error != AppError.Network) return@dataResult Result.Failure(error)

                    val albums =
                        albumDao
                            .getAll()
                            .map { albumMapper.roomToDomain(it) }

                    if (albums.isEmpty()) Result.Failure(error) else Result.Success(albums)
                }
            }
        }

    override suspend fun getAlbumInfo(
        artistName: String,
        albumName: String,
        mbId: String?,
    ): Result<Album> =
        dataResult {
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
                    Result.Failure(httpError(apiResult.code))
                }

                is ApiResult.Exception -> {
                    val exception = apiResult.throwable
                    if (exception !is Exception) throw exception
                    val error = exception.toAppError()
                    if (error != AppError.Network) return@dataResult Result.Failure(error)

                    val album =
                        albumDao
                            .getAlbum(artistName, albumName, mbId)
                            .let { cached -> cached?.let { albumMapper.roomToDomain(it) } }

                    if (album == null) Result.Failure(error) else Result.Success(album)
                }
            }
        }
}
