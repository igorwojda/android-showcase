package com.igorwojda.showcase.favourite

import com.igorwojda.showcase.favourite.data.dataModule
import com.igorwojda.showcase.favourite.domain.domainModule
import com.igorwojda.showcase.favourite.presentation.presentationModule

val featureFavouriteModules = listOf(
    presentationModule,
    domainModule,
    dataModule,
)
