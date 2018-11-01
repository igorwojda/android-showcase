package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_album_list.*

class AlbumListActivity : BaseActivity(), AlbumListView {
    override val layoutResourceId = R.layout.activity_album_list

    //Should be injected
    private val presenter = AlbumListPresenter()
    private lateinit var linearLyoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLyoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLyoutManager
        }
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
