package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.extension.instanceOf
import com.igorwojda.minimercari.feature.base.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_album_list.*

class AlbumListFragment : BaseFragment() {
    companion object {
        fun newInstance() = instanceOf<AlbumListFragment>()
    }

    override val layoutResourceId = R.layout.fragment_album_list

    private var albumAdapter = AlbumAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        albumAdapter.setOnClickListener { albumDomainModel ->
            context?.let {
                startActivity(AlbumDetailsActivity.getStartIntent(it, albumDomainModel.id))
            }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = albumAdapter
        }
    }

//    override fun setAlbums(list: List<AlbumDomainModel>) {
//        albumAdapter.albums = list
//        albumAdapter.notifyDataSetChanged()
//    }
}
