package com.igorwojda.lastfm.feature.album.presentation.albumdetails

import android.os.Bundle
import android.view.View
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.BaseFragment
import com.igorwojda.lastfm.feature.base.presentation.extension.getStringOrThrow
import com.igorwojda.lastfm.feature.base.presentation.extension.instanceOf
import com.igorwojda.lastfm.feature.base.presentation.extension.observeNotNull
import com.igorwojda.lastfm.feature.base.presentation.extension.withViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : BaseFragment() {

    companion object {
        private const val EXTRA_ALBUM_NAME = "EXTRA_ALBUM_NAME"
        private const val EXTRA_ARTIST_NAME = "EXTRA_ARTIST_NAME"
        private const val EXTRA_MB_ID = "EXTRA_MB_ID"

        fun newInstance(artistName: String, albumName: String, mbId: String?) = instanceOf<AlbumDetailFragment>(
            EXTRA_ALBUM_NAME to albumName,
            EXTRA_ARTIST_NAME to artistName,
            EXTRA_MB_ID to mbId
        )
    }

    override val layoutResourceId = R.layout.fragment_album_detail

    // This is injected here only because ViewModel injection is not implemented
    private val getAlbumUseCase: GetAlbumUseCase by instance()
    private val picasso: Picasso by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val albumName = arguments.getStringOrThrow(EXTRA_ALBUM_NAME)
        val artistName = arguments.getStringOrThrow(EXTRA_ARTIST_NAME)
        val mbId = arguments?.getString(EXTRA_MB_ID)

        // ViewModel injection is not implemented
        withViewModel({
            AlbumDetailsViewModel(
                getAlbumUseCase
            )
        }) {
            observeNotNull(state, ::onStateChange)
            getAlbum(artistName, albumName, mbId)
        }

        activity?.title = resources.getString(R.string.album_details)
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
