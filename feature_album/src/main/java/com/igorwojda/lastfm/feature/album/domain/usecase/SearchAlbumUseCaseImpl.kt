package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.domain.model.OldAlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository

interface SearchAlbumUseCase {
    suspend fun execute(phrase: String): List<OldAlbumDomainModel>
}

class SearchAlbumUseCaseImpl(
    private val albumRepository: AlbumRepository
) : SearchAlbumUseCase {
    override suspend fun execute(phrase: String) = albumRepository.searchAlbum(phrase)
}
