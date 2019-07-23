package com.igorwojda.showcase.feature.album

import android.content.Context
import com.igorwojda.showcase.app.feature.AlbumFeature
import com.igorwojda.showcase.feature.album.presentation.albumsearch.AlbumSearchActivity

class AlbumFeatureImpl : AlbumFeature {
    override fun navigateToAlbumSearch(context: Context) {
        AlbumSearchActivity.start(context)
    }
}
