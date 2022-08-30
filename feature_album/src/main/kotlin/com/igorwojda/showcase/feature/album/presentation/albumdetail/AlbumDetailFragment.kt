package com.igorwojda.showcase.feature.album.presentation.albumdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.igorwojda.showcase.base.presentation.ext.observe
import com.igorwojda.showcase.base.presentation.ext.visible
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.databinding.FragmentAlbumDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class AlbumDetailFragment : Fragment(R.layout.fragment_album_detail) {

    private val binding: FragmentAlbumDetailBinding by viewBinding()

    private val args: AlbumDetailFragmentArgs by navArgs()
    private val model: AlbumDetailViewModel by viewModel()
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

        observe(model.stateLiveData, stateObserver)

        model.onEnter(args)
    }

    companion object {
        const val imageSize = 800
    }
}
