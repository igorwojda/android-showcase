package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository

internal class SearchAlbumUseCase(
    private val albumRepository: AlbumRepository
) {

    suspend fun execute(phrase: String): List<AlbumDomainModel> =
        if (phrase.isBlank()) {
            listOf()
        } else {
            albumRepository.searchAlbum(phrase)
                .filter { it.images.isNotEmpty() }
        }
}
