package com.igorwojda.showcase.app

import com.igorwojda.showcase.app.gateway.AlbumGateway

@Suppress("detekt.UnsafeCast")
object FeatureManager {

    // Our gateway is implemented as a Kotlin object (singleton), se we access it with ".objectInstance"
    val albumGateway =
        Class.forName("com.igorwojda.showcase.feature.album.AlbumGatewayImpl").kotlin.objectInstance
            as AlbumGateway

    val kodeinModules = listOf(albumGateway.kodeinModule)
}
