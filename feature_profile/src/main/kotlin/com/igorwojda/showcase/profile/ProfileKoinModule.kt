package com.igorwojda.showcase.profile

import com.igorwojda.showcase.profile.data.dataModule
import com.igorwojda.showcase.profile.domain.domainModule
import com.igorwojda.showcase.profile.presentation.presentationModule

val featureProfilesModules = listOf(
    presentationModule,
    domainModule,
    dataModule,
)
