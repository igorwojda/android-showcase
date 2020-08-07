package com.igorwojda.showcase.feature.favourite

import com.igorwojda.showcase.app.feature.KodeinModuleProvider
import com.igorwojda.showcase.feature.favourite.data.dataModule
import com.igorwojda.showcase.feature.favourite.domain.domainModule
import com.igorwojda.showcase.feature.favourite.presentation.presentationModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Favourite"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${MODULE_NAME}Module") {
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}
