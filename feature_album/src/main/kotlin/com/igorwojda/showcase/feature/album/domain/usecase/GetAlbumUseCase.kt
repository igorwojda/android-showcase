package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.base.common.Result
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import java.io.IOException

internal class GetAlbumUseCase(
    private val albumRepository: AlbumRepository
) {

    suspend fun execute(
        artistName: String,
        albumName: String,
        mbId: String?,
    ): Result<Album> = try {
        albumRepository.getAlbumInfo(artistName, albumName, mbId)?.let {
            Result.Success(it)
        } ?: Result.Failure(RuntimeException("No data"))
    } catch (e: IOException) {
        Result.Failure(e)
    }
}
