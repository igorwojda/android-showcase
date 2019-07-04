package com.igorwojda.lastfm.feature.album.domain.di

import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCaseImpl
import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCase
import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

internal val albumDomainModule = Kodein.Module("albumDomainModule") {

    bind<SearchAlbumUseCase>() with provider { SearchAlbumUseCaseImpl(instance()) }

    bind<GetAlbumUseCase>() with provider { GetAlbumUseCaseImpl(instance()) }
}
