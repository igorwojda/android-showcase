package com.igorwojda.lastfm.feature.album.presentation

import androidx.fragment.app.Fragment
import com.igorwojda.lastfm.feature.album.presentation.albumdetails.AlbumDetailsViewModel
import com.igorwojda.lastfm.feature.album.presentation.albumsearch.AlbumSearchViewModel
import com.igorwojda.lastfm.feature.album.presentation.recyclerview.AlbumAdapter
import com.igorwojda.lastfm.feature.base.di.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val albumPresentationModule = Kodein.Module("albumPresentationModule") {

    // AlbumSearch
    bind<AlbumSearchViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { AlbumSearchViewModel(instance()) }
    }

    bind() from singleton { AlbumAdapter(instance()) }

    // AlbumDetails
    bind<AlbumDetailsViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { AlbumDetailsViewModel(instance()) }
    }
}
