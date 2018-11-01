package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity

class AlbumListActivity : BaseActivity() {
    override val layoutResourceId = R.layout.activity_album_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)
    }
}
