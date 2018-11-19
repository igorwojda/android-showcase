package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.extension.instanceOf
import com.igorwojda.minimercari.feature.base.presentation.BaseFragment

class AlbumDetailFragment : BaseFragment() {
    companion object {
        private const val EXTRA_ALBUM_ID = "EXTRA_ALBUM_ID"

        fun newInstance(albumId: Int) = instanceOf<AlbumDetailFragment>(
            EXTRA_ALBUM_ID to albumId
        )
    }

    override val layoutResourceId = R.layout.fragment_album_detail

//    override fun setAlbum(album: AlbumDomainModel) {
//        albumIdTextView.text = album.id.toString()
//        albumTitleTextView.text = album.title
//    }
}

//intent.extras.also {
//    var albumId = it?.getInt(AlbumDetailsActivity.EXTRA_ALBUM_ID)
//    requireNotNull(albumId)
//    presenter.getAlbum(albumId)
//}
