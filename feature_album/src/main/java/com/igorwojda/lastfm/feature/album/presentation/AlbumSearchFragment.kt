package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.presentation.recyclerview.AlbumAdapter
import com.igorwojda.lastfm.feature.base.presentation.BaseFragment
import com.igorwojda.lastfm.feature.base.presentation.extension.instanceOf
import com.igorwojda.lastfm.feature.base.presentation.extension.observeNotNull
import kotlinx.android.synthetic.main.fragment_album_list.*
import org.kodein.di.generic.instance

internal class AlbumSearchFragment : BaseFragment() {
    companion object {
        fun newInstance() = instanceOf<AlbumSearchFragment>()
    }

    override val layoutResourceId = R.layout.fragment_album_list

    private val viewModelFactory: AlbumListViewModelFactory by instance()
    private val albumAdapter: AlbumAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        albumAdapter.setOnClickListener { albumDomainModel ->
            context?.let {
                startActivity(
                    AlbumDetailsActivity.getStartIntent(
                        it, albumDomainModel.artist,
                        albumDomainModel.name, albumDomainModel.mbId
                    )
                )
            }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumAdapter
        }

        // ViewModel injection is not implemented
        ViewModelProviders.of(this, viewModelFactory).get(AlbumSearchViewModel::class.java).apply {
            observeNotNull(albumSearchLiveData, ::onAlbumListLiveData)
            init()
        }
    }

    private fun onAlbumListLiveData(list: List<AlbumDomainModel>) {
        albumAdapter.albums = list
        loadingSpinner.visibility = View.GONE
    }
}
