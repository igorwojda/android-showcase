package com.igorwojda.showcase.feature.profile

import com.igorwojda.showcase.app.feature.KodeinModuleProvider
import com.igorwojda.showcase.feature.profile.data.dataModule
import com.igorwojda.showcase.feature.profile.domain.domainModule
import com.igorwojda.showcase.feature.profile.presentation.presentationModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Profile"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${MODULE_NAME}Module") {
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}
