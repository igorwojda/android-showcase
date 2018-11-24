package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.repository.AlbumRepository

interface GetAlbumListUseCase {
    suspend fun execute(): List<AlbumDomainModel>
}

class GetAlbumListUseCaseImpl(
    private val albumRepository: AlbumRepository
) : GetAlbumListUseCase {
    override suspend fun execute() = albumRepository.getAlbumList()
}
