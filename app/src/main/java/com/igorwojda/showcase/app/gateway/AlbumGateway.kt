package com.igorwojda.showcase.app.gateway

import com.igorwojda.base.presentation.fragment.BaseContainerFragment

interface AlbumGateway : BaseGateway {

    fun createAlbumSearchFragment(): BaseContainerFragment
}
