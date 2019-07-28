package com.igorwojda.showcase.feature.album

import android.content.Context
import com.igorwojda.showcase.app.gateway.AlbumGateway
import com.igorwojda.showcase.feature.album.presentation.albumsearch.AlbumSearchActivity

object AlbumGatewayImpl : AlbumGateway {

    override val kodeinModule = albumModule

    override fun navigateToAlbumSearch(context: Context) {
        AlbumSearchActivity.start(context)
    }
}
