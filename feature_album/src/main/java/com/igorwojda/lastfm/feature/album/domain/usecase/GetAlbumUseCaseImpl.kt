package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository

interface GetAlbumUseCase {
    suspend fun execute(id: String): AlbumDomainModel?
}

class GetAlbumUseCaseImpl(
    private val albumRepository: AlbumRepository
) : GetAlbumUseCase {
    override suspend fun execute(id: String) = albumRepository.getAlbum(id)
}
