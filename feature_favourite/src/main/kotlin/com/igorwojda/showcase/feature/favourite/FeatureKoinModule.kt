package com.igorwojda.showcase.feature.favourite

import com.igorwojda.showcase.app.feature.KoinModuleProvider
import com.igorwojda.showcase.feature.favourite.data.dataModule
import com.igorwojda.showcase.feature.favourite.domain.domainModule
import com.igorwojda.showcase.feature.favourite.presentation.presentationModule

object FeatureKoinModuleProvider : KoinModuleProvider {

    override val modules = listOf(
        presentationModule,
        domainModule,
        dataModule
    )
}
