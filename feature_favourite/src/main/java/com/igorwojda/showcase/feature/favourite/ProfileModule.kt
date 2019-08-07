package com.igorwojda.showcase.feature.favourite

import com.igorwojda.showcase.feature.favourite.data.dataModule
import com.igorwojda.showcase.feature.favourite.domain.domainModule
import com.igorwojda.showcase.feature.favourite.presentation.presentationModule
import org.kodein.di.Kodein

internal const val FEATURE_NAME = "Favourite"

val favouriteModule = Kodein.Module("${FEATURE_NAME}Module") {
    import(presentationModule)
    import(domainModule)
    import(dataModule)
}
