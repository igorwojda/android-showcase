package com.igorwojda.showcase.feature.album.presentation.albumdetail

import android.os.Bundle
import android.view.View
import coil.api.load
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.library.base.presentation.extension.observe
import com.igorwojda.showcase.library.base.presentation.fragment.BaseContainerFragment
import com.pawegio.kandroid.visible
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : BaseContainerFragment() {

    private val viewModel: AlbumDetailViewModel by instance()

    override val layoutResourceId = R.layout.fragment_album_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, ::onStateChange)
        viewModel.loadData()
    }

    private fun onStateChange(state: AlbumDetailViewModel.ViewState) {
        progressBar.visible = state.isLoading

        nameTextView.text = state.albumName
        nameTextView.visible = state.albumName.isNotBlank()

        artistTextView.text = state.artistName
        artistTextView.visible = state.artistName.isNotBlank()

        errorAnimation.visible = state.isError

        val imageSize = 800

        coverImageView.load(state.coverImageUrl) {
            size(imageSize, imageSize)
        }
    }
}
