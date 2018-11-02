package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel

interface AlbumDetailsView {
    fun setAlbum(album: AlbumDomainModel)
}
