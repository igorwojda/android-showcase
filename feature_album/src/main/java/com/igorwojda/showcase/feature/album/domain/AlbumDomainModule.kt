package com.igorwojda.showcase.feature.album.domain

import com.igorwojda.showcase.feature.album.FEATURE_NAME
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.domain.usecase.SearchAlbumUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${FEATURE_NAME}DomainModule") {

    bind() from singleton { SearchAlbumUseCase(instance()) }

    bind() from singleton { GetAlbumUseCase(instance()) }
}
