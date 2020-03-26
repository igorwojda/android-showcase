package com.igorwojda.showcase.feature.album.presentation.albumdetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import coil.api.load
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.databinding.FragmentAlbumDetailBinding
import com.igorwojda.showcase.library.base.presentation.extension.observe
import com.igorwojda.showcase.library.base.presentation.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : BaseContainerFragment<FragmentAlbumDetailBinding>() {

    private val viewModel: AlbumDetailViewModel by instance()

    override val layoutResourceId = R.layout.fragment_album_detail

    override fun setupBinding(view: View) {
        binding.viewState = viewModel.stateLiveData.value
    }

    private val stateObserver = Observer<AlbumDetailViewModel.ViewState> {
        binding.viewState = it

        val imageSize = 800
        coverImageView.load(it.coverImageUrl) {
            size(imageSize, imageSize)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }
}
