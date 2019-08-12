package com.igorwojda.showcase.feature.album.presentation.albumdetails

import android.os.Bundle
import android.view.View
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.base.presentation.picasso.PicassoCallback
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailViewModel.ViewAction
import com.pawegio.kandroid.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : BaseContainerFragment() {

    override val viewModel: AlbumDetailViewModel by instance()

    private val picasso: Picasso by instance()

    override val layoutResourceId = R.layout.fragment_album_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.viewState, ::onStateChange)
    }

    private fun onStateChange(viewState: AlbumDetailViewModel.ViewState) {
        progressBar.visible = viewState.isProgressBarVisible

        nameTextView.text = viewState.name
        nameTextView.visible = viewState.name.isNotBlank()

        artistTextView.text = viewState.artist
        artistTextView.visible = viewState.artist.isNotBlank()

        if (!viewState.isImageLoading) {
            loadImage(viewState.imageUrl)
        }
    }

    private fun loadImage(it: String) {
        val imageSize = 800

        val callback = PicassoCallback().apply {
            onSuccess { viewModel.sendAction(ViewAction.ImageLoadingSuccess) }
            onError { viewModel.sendAction(ViewAction.ImageLoadingError) }
        }

        picasso.load(it)
            .resize(imageSize, imageSize)
            .centerCrop()
            .into(coverImageView, callback)
    }
}


