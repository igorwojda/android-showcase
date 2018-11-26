package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.domain.model.searchalbum.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository

interface GetAlbumUseCase {
    suspend fun execute(albumName: String, artistName: String): AlbumDomainModel?
}

class GetAlbumUseCaseImpl(
    private val albumRepository: AlbumRepository
) : GetAlbumUseCase {
    override suspend fun execute(albumName: String, artistName: String): AlbumDomainModel? =
        albumRepository.getAlbum(albumName, artistName)
}
