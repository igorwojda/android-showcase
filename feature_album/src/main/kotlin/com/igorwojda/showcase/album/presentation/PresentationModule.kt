package com.igorwojda.showcase.album.presentation

import coil.ImageLoader
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel
import com.igorwojda.showcase.album.presentation.screen.albumlist.AlbumListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

internal val presentationModule = module {

    // AlbumList
    viewModelOf(::AlbumListViewModel)

    single { ImageLoader(get()) }

    // AlbumDetails
    viewModelOf(::AlbumDetailViewModel)
}
