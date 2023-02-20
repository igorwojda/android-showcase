package com.igorwojda.showcase.feature.album

import com.igorwojda.showcase.feature.album.data.dataModule
import com.igorwojda.showcase.feature.album.domain.domainModule
import com.igorwojda.showcase.feature.album.presentation.presentationModule

val featureAlbumModules = listOf(
    presentationModule,
    domainModule,
    dataModule,
)
