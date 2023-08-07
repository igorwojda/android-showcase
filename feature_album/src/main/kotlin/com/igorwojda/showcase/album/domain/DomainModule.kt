package com.igorwojda.showcase.album.domain

import com.igorwojda.showcase.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.album.domain.usecase.GetAlbumUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val domainModule = module {

    singleOf(::GetAlbumListUseCase)

    singleOf(::GetAlbumUseCase)
}
