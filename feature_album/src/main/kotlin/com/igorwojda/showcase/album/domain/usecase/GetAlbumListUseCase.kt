package com.igorwojda.showcase.album.domain.usecase

import com.igorwojda.showcase.album.domain.model.Album
import com.igorwojda.showcase.album.domain.repository.AlbumRepository
import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.base.domain.result.mapSuccess

internal class GetAlbumListUseCase(
    private val albumRepository: AlbumRepository,
) {

    suspend operator fun invoke(query: String?): Result<List<Album>> {
        val result = albumRepository
            .searchAlbum(query)
            .mapSuccess {
                val albumsWithImages = value.filter { it.getDefaultImageUrl() != null }

                copy(value = albumsWithImages)
            }

        return result
    }
}
