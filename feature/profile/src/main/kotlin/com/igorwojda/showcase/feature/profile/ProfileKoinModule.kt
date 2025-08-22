package com.igorwojda.showcase.feature.profile

import com.igorwojda.showcase.feature.profile.data.dataModule
import com.igorwojda.showcase.feature.profile.domain.domainModule
import com.igorwojda.showcase.feature.profile.presentation.presentationModule

val featureProfilesModules =
    listOf(
        presentationModule,
        domainModule,
        dataModule,
    )
