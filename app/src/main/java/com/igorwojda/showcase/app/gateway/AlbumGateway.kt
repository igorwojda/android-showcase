package com.igorwojda.showcase.app.gateway

import androidx.fragment.app.Fragment

interface AlbumGateway : BaseGateway {

    fun getAlbumSearchFragment(): Fragment
}
