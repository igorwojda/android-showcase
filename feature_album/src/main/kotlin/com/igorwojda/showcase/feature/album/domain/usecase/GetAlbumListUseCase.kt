package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.base.common.Result
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository

internal class GetAlbumListUseCase(
    private val albumRepository: AlbumRepository
) {

    suspend fun execute(): Result<List<Album>> {
        val phrase = "Jackson"

        return when (val result = albumRepository.searchAlbum(phrase)) {
            is Result.Failure -> {
                result
            }
            is Result.Success -> {
                val albumsWithImages = result
                    .value
                    .filter { it.getDefaultImageUrl() != null }
                
                result.copy(value = albumsWithImages)
            }
        }
    }
}
