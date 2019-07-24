package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import com.igorwojda.showcase.R
import com.igorwojda.showcase.app.gateway.AlbumGateway
import com.igorwojda.showcase.base.presentation.activity.BaseContainerActivity

class MainActivity : BaseContainerActivity() {

    override val layoutResourceId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        try {
//            Class.forName(className).run { intentTo(className) }
//        } catch (e: ClassNotFoundException) {
//            null
//        }


        // Our gateway is implemented as a Kotlin object (singleton ), se we access it with ".objectInstance"
        val albumGateway =
            Class.forName("com.igorwojda.showcase.feature.album.AlbumGatewayImpl").kotlin.objectInstance
                as AlbumGateway

        albumGateway.navigateToAlbumSearch(this)
    }
}
