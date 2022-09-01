package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.base.common.Result
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.repository.AlbumRepository
import java.io.IOException

internal class GetAlbumListUseCase(
    private val albumRepository: AlbumRepository
) {

    suspend fun execute(): Result<List<Album>> {
        val phrase = "Jackson"

        return try {
            val albums = albumRepository
                .searchAlbum(phrase)
                .filter { it.getDefaultImageUrl() != null }
            
            Result.Success(albums)
        } catch (e: IOException) {
            Result.Failure(e)
        }
    }
}
