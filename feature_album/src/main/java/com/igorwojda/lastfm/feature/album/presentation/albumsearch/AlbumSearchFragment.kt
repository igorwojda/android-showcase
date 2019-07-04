package com.igorwojda.lastfm.feature.album.presentation.albumsearch

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.presentation.albumdetails.AlbumDetailsActivity
import com.igorwojda.lastfm.feature.album.presentation.recyclerview.AlbumAdapter
import com.igorwojda.lastfm.feature.base.presentation.BaseFragment
import com.igorwojda.lastfm.feature.base.presentation.extension.instanceOf
import com.igorwojda.lastfm.feature.base.presentation.extension.observeNotNull
import com.pawegio.kandroid.textWatcher
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_album_list.*
import org.kodein.di.generic.instance

internal class AlbumSearchFragment : BaseFragment() {
    companion object {
        fun newInstance() = instanceOf<AlbumSearchFragment>()
    }

    override val layoutResourceId = R.layout.fragment_album_list

    private val viewModelFactory: AlbumListViewModelFactory by instance()
    private val albumAdapter: AlbumAdapter by instance()
    private lateinit var albumSearchViewModel: AlbumSearchViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        albumAdapter.setOnDebouncedClickListener { albumDomainModel ->
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

        searchTextInput.textWatcher {
            afterTextChanged { editable: Editable? ->
                editable?.let { albumSearchViewModel.searchAlbum(it.toString()) }
                loadingSpinner.visible = true
            }
        }

        // ViewModel injection is not implemented
        albumSearchViewModel = ViewModelProviders.of(this, viewModelFactory).get(AlbumSearchViewModel::class.java)
        albumSearchViewModel.apply {
            observeNotNull(albumSearchLiveData, ::onAlbumListLiveData)
        }

        loadingSpinner.visible = false
    }

    private fun onAlbumListLiveData(list: List<AlbumDomainModel>) {
        albumAdapter.albums = list
        loadingSpinner.visible = false
    }
}
