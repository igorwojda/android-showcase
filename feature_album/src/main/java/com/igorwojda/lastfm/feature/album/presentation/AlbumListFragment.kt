package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.base.presentation.extension.instanceOf
import com.igorwojda.lastfm.feature.base.presentation.extension.observeNotNull
import com.igorwojda.minimercari.feature.base.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_album_list.*

class AlbumListFragment : BaseFragment() {
    companion object {
        fun newInstance() = instanceOf<AlbumListFragment>()
    }

    override val layoutResourceId = R.layout.fragment_album_list

    private var albumAdapter = AlbumAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

        val v = ViewModelProviders.of(this).get(AlbumListViewModel::class.java).also {
            it.albumListViewModel.observeNotNull(this, ::onAlbumListLiveData)

        }

        v.init()
    }

    private fun onAlbumListLiveData(list: List<AlbumDomainModel>) {
        albumAdapter.albums = list
        albumAdapter.notifyDataSetChanged()
    }
}
