package com.igorwojda.lastfm.feature.album.presentation.albumsearch

import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.presentation.albumdetails.AlbumDetailsActivity
import com.igorwojda.lastfm.feature.album.presentation.recyclerview.AlbumAdapter
import com.igorwojda.lastfm.feature.base.presentation.BaseFragment
import com.igorwojda.lastfm.feature.base.presentation.extension.observe
import com.pawegio.kandroid.hide
import com.pawegio.kandroid.show
import com.pawegio.kandroid.textWatcher
import kotlinx.android.synthetic.main.fragment_album_list.*
import org.kodein.di.generic.instance

internal class AlbumSearchFragment : BaseFragment() {

    override val layoutResourceId = R.layout.fragment_album_list

    private val viewModel: AlbumSearchViewModel by instance()
    private val albumAdapter: AlbumAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = requireNotNull(context)

        albumAdapter.setOnDebouncedClickListener {
            AlbumDetailsActivity.start(context, it.artist, it.name, it.mbId)
        }

        recyclerView.apply {
            setHasFixedSize(true)
            val numColumns = 2
            layoutManager = GridLayoutManager(context, numColumns)
            adapter = albumAdapter
        }

        searchTextInput.textWatcher {
            afterTextChanged { editable: Editable? ->
                editable?.let { viewModel.searchAlbum(it.toString()) }
                loadingSpinner.show()
            }
        }

        observe(viewModel.state, ::onStateChange)

        loadingSpinner.hide()
    }

    private fun onStateChange(list: List<AlbumDomainModel>) {
        albumAdapter.albums = list
        loadingSpinner.hide()
    }
}
