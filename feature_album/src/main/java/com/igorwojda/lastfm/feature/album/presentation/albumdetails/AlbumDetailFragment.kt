package com.igorwojda.lastfm.feature.album.presentation.albumdetails

import android.os.Bundle
import android.view.View
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.base.presentation.BaseFragment
import com.igorwojda.lastfm.feature.base.presentation.extension.observe
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : BaseFragment() {

    companion object {
        fun newInstance(artistName: String, albumName: String, mbId: String?) = AlbumDetailFragment().apply {
            this.artistName = artistName
            this.albumName = albumName
            this.mbId = mbId
        }
    }

    override val layoutResourceId = R.layout.fragment_album_detail

    var artistName by argument<String>()
    var albumName by argument<String>()
    var mbId by argument<String?>()

    private val viewModel: AlbumDetailsViewModel by instance()
    private val picasso: Picasso by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = resources.getString(R.string.album_details)

        observe(viewModel.state, ::onStateChange)
        viewModel.getAlbum(artistName, albumName, mbId)
    }

    private fun onStateChange(albumDomainModel: AlbumDomainModel) {
        nameTextView.text = albumDomainModel.name
        artistTextView.text = albumDomainModel.artist
        publishedTextView.text = albumDomainModel.wiki?.published

        val url = albumDomainModel.images.firstOrNull { it.size == AlbumDomainImageSize.LARGE }?.url
        if (albumDomainModel.images.isNotEmpty() && !url.isNullOrEmpty()) {
            loadImage(url)
        }
    }

    private fun loadImage(it: String) {
        val imageSize = 800

        picasso.load(it)
            .resize(imageSize, imageSize)
            .centerCrop()
            .placeholder(R.drawable.progress_animation)
            .into(imageView)
    }
}
