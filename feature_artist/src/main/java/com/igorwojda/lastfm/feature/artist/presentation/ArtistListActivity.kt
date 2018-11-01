package com.igorwojda.lastfm.feature.artist.presentation

import android.os.Bundle
import com.igorwojda.lastfm.feature.artist.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity

class ArtistListActivity : BaseActivity() {

    override val layoutResourceId = R.layout.activity_artist_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.feature_artist)
    }
}
