package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository

internal class GetAlbumListUseCase(
    private val albumRepository: AlbumRepository
) {
    suspend fun execute(): List<AlbumDomainModel> {
        // Due to API limitations we have to perform search with custom phrase to get albums
        val phrase = "sd"

        return albumRepository.searchAlbum(phrase)
            .filter { it.images.isNotEmpty() }
    }
}
