package com.igorwojda.lastfm.feature.album.data.repository

import com.igorwojda.lastfm.feature.album.domain.model.ArtistDomainModel

class AlbumNetworkRepository : AlbumRepository {
    override fun getAlbumList(): List<ArtistDomainModel> {

        return listOf()
    }
}
