package com.igorwojda.showcase.feature.album

import com.igorwojda.showcase.app.feature.KoinModuleProvider
import com.igorwojda.showcase.feature.album.data.dataModule
import com.igorwojda.showcase.feature.album.domain.domainModule
import com.igorwojda.showcase.feature.album.presentation.presentationModule

object FeatureKoinModuleProvider : KoinModuleProvider {

    override val modules = listOf(
        presentationModule,
        domainModule,
        dataModule
    )
}
