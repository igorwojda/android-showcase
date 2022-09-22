package com.igorwojda.showcase.feature.album.presentation.albumlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.igorwojda.showcase.base.presentation.ext.visible
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.databinding.FragmentAlbumListBinding
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.AlbumAdapter
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.GridAutofitLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumListFragment : Fragment(R.layout.fragment_album_list) {

    private val binding: FragmentAlbumListBinding by viewBinding()
    private val model: AlbumListViewModel by viewModel()
    private val albumAdapter: AlbumAdapter by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = requireContext()

        albumAdapter.setOnDebouncedClickListener {
            model.onAlbumClick(it)
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

        model.onEnter()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            model.stateFlow.collect {
                albumAdapter.albums = it.albums
                binding.progressBar.visible = it.isLoading
                binding.errorAnimation.visible = it.isError
            }
        }
    }
}
