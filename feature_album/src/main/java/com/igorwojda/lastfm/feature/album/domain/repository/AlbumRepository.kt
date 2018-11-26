package com.igorwojda.lastfm.feature.album.domain.repository

import com.igorwojda.lastfm.feature.album.domain.model.OldAlbumDomainModel

interface AlbumRepository {
    suspend fun getAlbum(id: String): OldAlbumDomainModel?

    suspend fun searchAlbum(phrase: String): List<OldAlbumDomainModel>
}
