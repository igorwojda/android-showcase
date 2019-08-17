package com.igorwojda.showcase.feature.album.presentation.albumdetail

import android.os.Bundle
import android.view.View
import coil.api.load
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.feature.album.R
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : BaseContainerFragment() {

    private val viewModel: AlbumDetailViewModel by instance()

    override val layoutResourceId = R.layout.fragment_album_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, ::onStateChange)
    }

    private fun onStateChange(state: AlbumDetailViewModel.ViewState) {
        progressBar.visible = state.isLoading

        nameTextView.text = state.name
        nameTextView.visible = state.name.isNotBlank()

        artistTextView.text = state.artist
        artistTextView.visible = state.artist.isNotBlank()

        errorAnimation.visible = state.isError

        val imageSize = 800

        coverImageView.load(state.coverImageUrl) {
            size(imageSize, imageSize)
        }
    }
}


