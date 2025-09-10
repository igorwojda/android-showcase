package com.igorwojda.showcase.feature.settings

import com.igorwojda.showcase.feature.settings.data.dataModule
import com.igorwojda.showcase.feature.settings.domain.domainModule
import com.igorwojda.showcase.feature.settings.presentation.presentationModule

val featureSettingsModules =
    listOf(
        presentationModule,
        domainModule,
        dataModule,
    )
