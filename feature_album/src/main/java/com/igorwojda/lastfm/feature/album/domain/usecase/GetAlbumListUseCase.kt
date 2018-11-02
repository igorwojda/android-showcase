package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.data.repository.AlbumNetworkRepository
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.base.domain.BaseUseCase

class GetAlbumListUseCase : BaseUseCase<List<AlbumDomainModel>>() {
    private val albumRepository = AlbumNetworkRepository()

    override suspend fun execute(): List<AlbumDomainModel> {
        val albumList = albumRepository.getAlbumList()
        return albumList
    }
}
