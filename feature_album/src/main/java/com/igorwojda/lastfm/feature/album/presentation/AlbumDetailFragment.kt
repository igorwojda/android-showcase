package com.igorwojda.lastfm.feature.album.presentation

import android.os.Bundle
import android.view.View
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.OldAlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.BaseFragment
import com.igorwojda.lastfm.feature.base.presentation.extension.instanceOf
import com.igorwojda.lastfm.feature.base.presentation.extension.observeNotNull
import com.igorwojda.lastfm.feature.base.presentation.extension.withViewModel
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

class AlbumDetailFragment : BaseFragment() {
    companion object {
        private const val EXTRA_ALBUM_ID = "EXTRA_ALBUM_ID"

        fun newInstance(albumId: String) = instanceOf<AlbumDetailFragment>(
            EXTRA_ALBUM_ID to albumId
        )
    }

    override val layoutResourceId = R.layout.fragment_album_detail

    private val getAlbumUseCase: GetAlbumUseCase by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val albumId = arguments?.getString(EXTRA_ALBUM_ID)
        requireNotNull(albumId) { "albumId is null" }

        withViewModel({ AlbumDetailsViewModel(getAlbumUseCase) }) {
            observeNotNull(albumLiveData, ::onAlbumLiveData)
            init(albumId)
        }
    }

    private fun onAlbumLiveData(albumDomainModel: OldAlbumDomainModel) {
        albumIdTextView.text = albumDomainModel.id.toString()
        albumTitleTextView.text = albumDomainModel.title
    }
}
