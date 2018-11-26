package com.igorwojda.lastfm.feature.album.presentation.di

import com.igorwojda.lastfm.feature.album.presentation.AlbumListViewModelFactory
import com.igorwojda.lastfm.feature.album.presentation.recyclerview.AlbumAdapter
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val albumPresentationModule = Kodein.Module("albumPresentationModule") {
    bind() from provider { AlbumListViewModelFactory(instance()) }
    bind() from singleton { AlbumAdapter(instance()) }
}
