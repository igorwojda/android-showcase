package com.igorwojda.showcase.feature.album

import com.igorwojda.showcase.app.feature.AlbumGateway
import com.igorwojda.showcase.feature.album.data.dataModule
import com.igorwojda.showcase.feature.album.domain.domainModule
import com.igorwojda.showcase.feature.album.presentation.presentationModule
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

internal const val FEATURE_NAME = "Album"

val albumModule = Kodein.Module("${FEATURE_NAME}Module") {

    bind<AlbumGateway>() with provider { AlbumGatewayImpl() }

    import(presentationModule)
    import(domainModule)
    import(dataModule)
}
