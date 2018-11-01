package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity

class AlbumListActivity : BaseActivity(), AlbumListView {
    override val layoutResourceId = R.layout.activity_album_list

    //Should be injected
    private val presenter = AlbumListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }
}
