package com.igorwojda.showcase.feature.album.presentation

import androidx.fragment.app.Fragment
import coil.ImageLoader
import com.igorwojda.showcase.base.di.KotlinViewModelProvider
import com.igorwojda.showcase.feature.album.FEATURE_NAME
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailViewModel
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.AlbumAdapter
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${FEATURE_NAME}PresentationModule") {

    // AlbumList
    bind<AlbumListViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { AlbumListViewModel(instance()) }
    }

    bind() from singleton { AlbumAdapter(instance()) }

    bind() from singleton { ImageLoader(instance()) }

    // AlbumDetails
    bind<AlbumDetailViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { AlbumDetailViewModel(instance(), instance(), instance()) }
    }
}
