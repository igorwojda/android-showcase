package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.base.common.Result
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository

internal class GetAlbumUseCase(
    private val albumRepository: AlbumRepository
) {

    suspend fun execute(
        artistName: String,
        albumName: String,
        mbId: String?,
    ): Result<Album> = albumRepository.getAlbumInfo(artistName, albumName, mbId)
}
