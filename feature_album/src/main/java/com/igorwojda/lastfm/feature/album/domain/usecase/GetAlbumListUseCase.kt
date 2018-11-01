package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.data.repository.AlbumNetworkRepository
import com.igorwojda.lastfm.feature.base.domain.BaseUseCase

class GetAlbumListUseCase : BaseUseCase() {
    val albumRepository = AlbumNetworkRepository()

    override fun execute() {
        albumRepository.getAlbumList()
    }
}
