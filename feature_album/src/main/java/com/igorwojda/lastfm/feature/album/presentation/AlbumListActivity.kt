package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_album_list.*
import timber.log.Timber

class AlbumListActivity : BaseActivity(), AlbumListView {
    override val layoutResourceId = R.layout.activity_album_list

    //Todo: should be injected
    private val presenter = AlbumListPresenter()
    private var albumAdapter = AlbumAdapter()
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = albumAdapter
        }
    }

    //Todo: should be done in BaseActivity
    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    //Todo: should be done in BaseActivity
    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun setAlbums(list: List<AlbumDomainModel>) {
        albumAdapter.albumList = listOf(
            AlbumDomainModel(1, 1, "AAA"),
            AlbumDomainModel(2, 2, "BBB")
        )//list
        Timber.d("111 ${Looper.myLooper().toString()}")
        albumAdapter.notifyDataSetChanged()
    }
}
