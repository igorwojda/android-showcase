package com.igorwojda.showcase.feature.album.presentation.albumdetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import coil.load
import com.igorwojda.showcase.base.delegate.viewBinding
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.extension.visible
import com.igorwojda.showcase.base.presentation.fragment.InjectionFragment
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.databinding.FragmentAlbumDetailBinding
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : InjectionFragment(R.layout.fragment_album_detail) {

    companion object {
        const val imageSize = 800
    }

    private val binding: FragmentAlbumDetailBinding by viewBinding()

    private val viewModel: AlbumDetailViewModel by instance()

    private val stateObserver = Observer<AlbumDetailViewModel.ViewState> {
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

        viewModel.loadData()
    }
}
