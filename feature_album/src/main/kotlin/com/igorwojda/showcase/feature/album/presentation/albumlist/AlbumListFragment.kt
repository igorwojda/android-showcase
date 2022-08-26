package com.igorwojda.showcase.feature.album.presentation.albumlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.extension.visible
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.databinding.FragmentAlbumListBinding
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.AlbumAdapter
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.GridAutofitLayoutManager
import org.koin.android.ext.android.inject

class AlbumListFragment : Fragment(R.layout.fragment_album_list) {

    private val binding: FragmentAlbumListBinding by viewBinding()

    private val viewModel: AlbumListViewModel by inject()

    private val albumAdapter: AlbumAdapter by inject()

    private val stateObserver = Observer<AlbumListViewModel.ViewState> {
        albumAdapter.albums = it.albums

        binding.progressBar.visible = it.isLoading
        binding.errorAnimation.visible = it.isError
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = requireContext()

        albumAdapter.setOnDebouncedClickListener {
            viewModel.navigateToAlbumDetails(it.artist, it.name, it.mbId)
        }

        binding.recyclerView.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.getDimension(R.dimen.image_size).toInt()
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth
                )
            adapter = albumAdapter
        }

        observe(viewModel.stateLiveData, stateObserver)

        viewModel.loadData()
    }
}
