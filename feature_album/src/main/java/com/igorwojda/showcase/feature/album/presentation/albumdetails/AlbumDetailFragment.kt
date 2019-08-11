package com.igorwojda.showcase.feature.album.presentation.albumdetails

import android.os.Bundle
import android.view.View
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.base.presentation.picasso.PicassoCallback
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.pawegio.kandroid.hide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : BaseContainerFragment() {

    override val viewModel: AlbumDetailViewModel by instance()

    private val picasso: Picasso by instance()

    override val layoutResourceId = R.layout.fragment_album_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = resources.getString(R.string.album_details)

        observe(viewModel.state, ::onStateChange)
    }

    private fun onStateChange(albumDomainModel: AlbumDomainModel) {
        nameTextView.text = albumDomainModel.name
        artistTextView.text = albumDomainModel.artist

        val url = albumDomainModel.images.firstOrNull { it.size == AlbumDomainImageSize.LARGE }?.url
        if (albumDomainModel.images.isNotEmpty() && !url.isNullOrEmpty()) {
            loadImage(url)
        }
    }

    private fun loadImage(it: String) {
        val imageSize = 800

        val callback = PicassoCallback().apply {
            onSuccess {
                progressBar.hide()
//                artistInfoContainer.show()
            }
        }

        picasso.load(it)
            .resize(imageSize, imageSize)
            .centerCrop()
            .placeholder(R.drawable.progress_animation)
            .into(coverImageView, callback)
    }
}
