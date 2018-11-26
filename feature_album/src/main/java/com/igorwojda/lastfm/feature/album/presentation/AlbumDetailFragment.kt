package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import android.view.View
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.BaseFragment
import com.igorwojda.lastfm.feature.base.presentation.extension.instanceOf
import com.igorwojda.lastfm.feature.base.presentation.extension.observeNotNull
import com.igorwojda.lastfm.feature.base.presentation.extension.withViewModel
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

class AlbumDetailFragment : BaseFragment() {
    companion object {
        private const val EXTRA_ALBUM_NAME = "EXTRA_ALBUM_NAME"
        private const val EXTRA_ARTIST_NAME = "EXTRA_ARTIST_NAME"
        private const val EXTRA_MB_ID = "EXTRA_MB_ID"

        fun newInstance(artistName: String, albumName: String, mbId: String) = instanceOf<AlbumDetailFragment>(
            EXTRA_ALBUM_NAME to albumName,
            EXTRA_ARTIST_NAME to artistName,
            EXTRA_MB_ID to mbId
        )
    }

    override val layoutResourceId = R.layout.fragment_album_detail

    private val getAlbumUseCase: GetAlbumUseCase by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val albumName = arguments?.getString(EXTRA_ALBUM_NAME)
        require(!albumName.isNullOrEmpty()) { "$EXTRA_ALBUM_NAME is null" }

        val artistName = arguments?.getString(EXTRA_ARTIST_NAME)
        require(!artistName.isNullOrEmpty()) { "$EXTRA_ARTIST_NAME is null" }

        val mbId = arguments?.getString(EXTRA_MB_ID)
        require(!mbId.isNullOrEmpty()) { "$EXTRA_MB_ID is null" }

        withViewModel({ AlbumDetailsViewModel(getAlbumUseCase) }) {
            observeNotNull(albumLiveData, ::onAlbumLiveData)
            init(artistName, albumName, mbId)
        }
    }

    private fun onAlbumLiveData(albumDomainModel: AlbumDomainModel) {
        albumNameTextView.text = albumDomainModel.name
        albumArtistTextView.text = albumDomainModel.artist
    }
}
