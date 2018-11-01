package com.igorwojda.lastfm.feature.album.data.repository

import com.igorwojda.lastfm.feature.album.domain.model.ArtistDomainModel

interface AlbumRepository {
    fun getAlbumList(): List<ArtistDomainModel>
}
