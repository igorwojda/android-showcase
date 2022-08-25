package com.igorwojda.showcase.feature.profile

import com.igorwojda.showcase.app.feature.KoinModuleProvider
import com.igorwojda.showcase.feature.profile.data.dataModule
import com.igorwojda.showcase.feature.profile.domain.domainModule
import com.igorwojda.showcase.feature.profile.presentation.presentationModule

internal const val MODULE_NAME = "Profile"

object FeatureKoinModuleProvider : KoinModuleProvider {

    override val modules = listOf(
        presentationModule,
        domainModule,
        dataModule
    )
}
