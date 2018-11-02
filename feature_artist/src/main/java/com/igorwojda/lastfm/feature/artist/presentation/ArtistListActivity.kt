package com.igorwojda.lastfm.feature.artist.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.igorwojda.lastfm.feature.artist.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity

class ArtistListActivity : BaseActivity(), ArtistListView {
    companion object {
        fun getStartIntent(context: Context) = Intent(context, ArtistListActivity::class.java)
    }

    override val layoutResourceId = R.layout.activity_artist_list

    // Todo: should be injected
    private val presenter = ArtistListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.feature_artist)
    }

    //  Todo: should be done in BaseActivity
    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    // Todo: should be done in BaseActivity
    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }
}
