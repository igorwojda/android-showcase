package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository

internal class GetAlbumUseCase(
    private val albumRepository: AlbumRepository
) {
    suspend fun execute(
        artistName: String,
        albumName: String,
        mbId: String?
    ): AlbumDomainModel? =
        albumRepository.getAlbumInfo(artistName, albumName, mbId)
}
