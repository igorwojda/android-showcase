package com.igorwojda.showcase.feature.album

import com.igorwojda.showcase.app.gateway.AlbumGateway
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListFragment

object AlbumGatewayImpl : AlbumGateway {

    override val kodeinModule = albumModule

    override fun createAlbumSearchFragment() = AlbumListFragment()
}
