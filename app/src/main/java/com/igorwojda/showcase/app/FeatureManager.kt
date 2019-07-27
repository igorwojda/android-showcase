package com.igorwojda.showcase.app

import com.igorwojda.showcase.app.gateway.AlbumGateway

// Dynamic Feature modules require reversed dependency (dynamic feature module depends on app module)
// This means we have to use reflection to access module content
// See: https://medium.com/mindorks/dynamic-feature-modules-the-future-4bee124c0f1
@Suppress("detekt.UnsafeCast")
object FeatureManager {

    // Our gateway is implemented as a Kotlin object (singleton), se we access it with ".objectInstance"
    val albumGateway =
        Class.forName("com.igorwojda.showcase.feature.album.AlbumGatewayImpl").kotlin.objectInstance
            as AlbumGateway

    val kodeinModules = listOf(albumGateway.kodeinModule)
}
