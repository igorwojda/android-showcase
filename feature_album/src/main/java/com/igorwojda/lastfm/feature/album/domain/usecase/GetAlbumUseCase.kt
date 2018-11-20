package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.data.repository.AlbumNetworkRepository

class GetAlbumUseCase(
    private val albumRepository: AlbumNetworkRepository = AlbumNetworkRepository()
) {
    suspend fun execute(id: Int) = albumRepository.getAlbum(id)
}
