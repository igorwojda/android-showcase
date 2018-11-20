package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import android.view.View
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.base.presentation.extension.instanceOf
import com.igorwojda.lastfm.feature.base.presentation.extension.observeNotNull
import com.igorwojda.lastfm.feature.base.presentation.extension.withViewModel
import com.igorwojda.minimercari.feature.base.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_album_detail.*

class AlbumDetailFragment : BaseFragment() {
    companion object {
        private const val EXTRA_ALBUM_ID = "EXTRA_ALBUM_ID"

        fun newInstance(albumId: Int) = instanceOf<AlbumDetailFragment>(
            EXTRA_ALBUM_ID to albumId
        )
    }

    override val layoutResourceId = R.layout.fragment_album_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val albumId = arguments?.getInt(EXTRA_ALBUM_ID)
        requireNotNull(albumId) { "albumId is null" }

        withViewModel({ AlbumDetailsViewModel(albumId) }) {
            observeNotNull(albumDetailLiveData, ::onAlbumDetailsLiveData)
            init()
        }
    }

    private fun onAlbumDetailsLiveData(albumDomainModel: AlbumDomainModel) {
        albumIdTextView.text = albumDomainModel.id.toString()
        albumTitleTextView.text = albumDomainModel.title
    }
}
