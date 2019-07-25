package com.igorwojda.showcase.feature.album

import com.igorwojda.showcase.feature.album.data.dataModule
import com.igorwojda.showcase.feature.album.domain.domainModule
import com.igorwojda.showcase.feature.album.presentation.presentationModule
import org.kodein.di.Kodein

internal const val FEATURE_NAME = "Album"

val albumModule = Kodein.Module("${FEATURE_NAME}Module") {
    import(presentationModule)
    import(domainModule)
    import(dataModule)
}
