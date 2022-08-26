package com.igorwojda.showcase.feature.album.presentation.albumdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.extension.visible
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.databinding.FragmentAlbumDetailBinding
import org.koin.android.ext.android.inject

internal class AlbumDetailFragment : Fragment(R.layout.fragment_album_detail) {

    companion object {
        const val imageSize = 800
    }

    private val binding: FragmentAlbumDetailBinding by viewBinding()

    private val viewModel: AlbumDetailViewModel by inject()

    private val stateObserver = Observer<AlbumDetailViewModel.State> {
        binding.progressBar.visible = it.isLoading

        binding.nameTextView.text = it.albumName
        binding.nameTextView.visible = it.albumName.isNotBlank()

        binding.artistTextView.text = it.artistName
        binding.artistTextView.visible = it.artistName.isNotBlank()

        binding.errorAnimation.visible = it.isError

        binding.coverImageView.load(it.coverImageUrl) {
            size(imageSize, imageSize)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, stateObserver)

        viewModel.onEnter()
    }
}
