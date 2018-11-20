package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.data.repository.AlbumNetworkRepository

class GetAlbumListUseCase(
    private val albumRepository: AlbumNetworkRepository = AlbumNetworkRepository()
) {
    suspend fun execute() = albumRepository.getAlbumList()
}
