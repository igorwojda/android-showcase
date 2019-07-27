package com.igorwojda.showcase.app.gateway

import android.content.Context

interface AlbumGateway : BaseGateway {

    fun navigateToAlbumSearch(context: Context)
}
