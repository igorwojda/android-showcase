package com.igorwojda.lastfm.feature.album.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_album_list.*

class AlbumListActivity : BaseActivity(), AlbumListView {
    companion object {
        fun getStartIntent(context: Context) = Intent(context, AlbumListActivity::class.java)
    }

    override val layoutResourceId = R.layout.activity_album_list

    // Todo: should be injected
    private val presenter = AlbumListPresenter()
    private var albumAdapter = AlbumAdapter()
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title = resources.getString(R.string.feature_album)

        albumAdapter.setOnClickListener {
            startActivity(AlbumDetailsActivity.getStartIntent(this, it.id))
        }

        linearLayoutManager = LinearLayoutManager(this)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = albumAdapter
        }
    }

    // Todo: should be done in BaseActivity
    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    // Todo: should be done in BaseActivity
    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun setAlbums(list: List<AlbumDomainModel>) {
        albumAdapter.albums = list
        albumAdapter.notifyDataSetChanged()
    }
}
