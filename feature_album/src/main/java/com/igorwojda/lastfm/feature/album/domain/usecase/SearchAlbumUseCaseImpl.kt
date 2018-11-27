package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository
import timber.log.Timber

internal interface SearchAlbumUseCase {
    suspend fun execute(phrase: String): List<AlbumDomainModel>
}

internal class SearchAlbumUseCaseImpl(
    private val albumRepository: AlbumRepository
) : SearchAlbumUseCase {
    override suspend fun execute(phrase: String) = albumRepository.searchAlbum(phrase).also {
        Timber.d("AAA SearchAlbumUseCaseImpl $phrase")
    }
}
