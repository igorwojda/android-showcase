package com.igorwojda.showcase.album.domain.usecase

import com.igorwojda.showcase.album.domain.model.Album
import com.igorwojda.showcase.album.domain.repository.AlbumRepository
import com.igorwojda.showcase.base.domain.result.Result

internal class GetAlbumUseCase(
    private val albumRepository: AlbumRepository,
) {

    suspend operator fun invoke(
        artistName: String,
        albumName: String,
        mbId: String?,
    ): Result<Album> = albumRepository.getAlbumInfo(artistName, albumName, mbId)
}
