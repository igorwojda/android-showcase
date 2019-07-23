package com.igorwojda.showcase.feature.album

import android.content.Context
import com.igorwojda.showcase.app.feature.AlbumFeature
import com.igorwojda.showcase.appModule
import com.igorwojda.showcase.feature.album.presentation.albumsearch.AlbumSearchActivity
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

class AlbumFeatureImpl : AlbumFeature, KodeinAware {

    override val kodein by Kodein.lazy {
        import(appModule)
        import(albumModule)
    }

    override fun navigateToAlbumSearch(context: Context) {
        AlbumSearchActivity.start(context)
    }
}
