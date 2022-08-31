package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.base.common.Result
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import java.io.IOException

internal class GetAlbumListUseCase(
    private val albumRepository: AlbumRepository
) {

    suspend fun execute(): Result<List<Album>> {
        // Due to API limitations search with custom phrase have to be performed to get albums
        val phrase = "Jackson"

        return try {
            Result.Success(albumRepository.searchAlbum(phrase).filter { it.getDefaultImageUrl() != null })
        } catch (e: IOException) {
            Result.Failure(e)
        }
    }
}
