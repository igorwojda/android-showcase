package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import com.igorwojda.base.presentation.activity.BaseContainerActivity
import com.igorwojda.showcase.R
import com.igorwojda.showcase.app.gateway.AlbumGateway
import org.kodein.di.generic.instance

class MainActivity : BaseContainerActivity() {

    override val layoutResourceId = R.layout.activity_main

    private val albumGateway: AlbumGateway by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        albumGateway.navigateToAlbumSearch(this)
    }
}
