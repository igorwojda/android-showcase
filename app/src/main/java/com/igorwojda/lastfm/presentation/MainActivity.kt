package com.igorwojda.lastfm.presentation

import android.os.Bundle
import com.igorwojda.lastfm.R
import com.igorwojda.lastfm.feature.album.presentation.albumsearch.AlbumSearchActivity
import com.igorwojda.lastfm.feature.base.presentation.activity.BaseContainerActivity

class MainActivity : BaseContainerActivity() {

    override val layoutResourceId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AlbumSearchActivity.start(this)
    }
}
