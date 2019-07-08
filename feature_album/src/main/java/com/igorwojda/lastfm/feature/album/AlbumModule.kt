package com.igorwojda.lastfm.feature.album

import com.igorwojda.lastfm.feature.album.data.dataModule
import com.igorwojda.lastfm.feature.album.domain.domainModule
import com.igorwojda.lastfm.feature.album.presentation.presentationModule
import org.kodein.di.Kodein

internal const val FEATURE_NAME = "featureAlbum"

val albumModule = Kodein.Module("${FEATURE_NAME}Module") {
    import(presentationModule)
    import(domainModule)
    import(dataModule)
}
