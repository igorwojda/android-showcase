package com.igorwojda.showcase.album

import com.igorwojda.showcase.album.domain.domainModule
import com.igorwojda.showcase.album.presentation.presentationModule

val featureAlbumModules = listOf(
    presentationModule,
    domainModule,
    com.igorwojda.showcase.album.data.dataModule,
)
