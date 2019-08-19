package com.igorwojda.showcase.feature.album.presentation.albumlist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.igorwojda.showcase.base.presentation.extension.dimension
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.AlbumAdapter
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.GridAutofitLayoutManager
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_album_list.*
import org.kodein.di.generic.instance

class AlbumListFragment : BaseContainerFragment() {

    private val viewModel: AlbumListViewModel by instance()

    override val layoutResourceId = R.layout.fragment_album_list

    private val albumAdapter: AlbumAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager =
                GridAutofitLayoutManager(
                    requireContext(),
                    columnWidth = context.dimension(R.dimen.image_size).toInt()
                )
            adapter = albumAdapter.apply {
                setOnDebouncedClickListener {
                    val navDirections = AlbumListFragmentDirections.actionAlbumListToAlbumDetail(it.artist, it.name, it.mbId)
                    findNavController().navigate(navDirections)
                }
            }
        }
        observe(viewModel.stateLiveData, ::onStateChange)
        viewModel.loadData()
    }

    private fun onStateChange(state: AlbumListViewModel.ViewState) {
        albumAdapter.albums = state.albums
        progressBar.visible = state.isLoading
        errorAnimation.visible = state.isError
    }
}
