package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.base.domain.result.mapSuccess
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository

internal class GetAlbumListUseCase(
    private val albumRepository: AlbumRepository,
) {

    suspend operator fun invoke(): Result<List<Album>> {
        val phrase = "Jackson"

        val result = albumRepository
            .searchAlbum(phrase)
            .mapSuccess {
                val albumsWithImages = value.filter { it.getDefaultImageUrl() != null }

                copy(value = albumsWithImages)
            }

        return result
    }
}
