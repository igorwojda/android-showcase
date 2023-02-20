package com.igorwojda.showcase.feature.favourite

import com.igorwojda.showcase.feature.favourite.data.dataModule
import com.igorwojda.showcase.feature.favourite.domain.domainModule
import com.igorwojda.showcase.feature.favourite.presentation.presentationModule

val featureFavouriteModules = listOf(
    presentationModule,
    domainModule,
    dataModule,
)
