package com.igorwojda.lastfm.feature.album.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_album_details.*

class AlbumDetailsActivity : BaseActivity(), AlbumDetailsView {
    companion object {
        private const val EXTRA_ALBUM_ID = "EXTRA_ALBUM_ID"

        fun getStartIntent(context: Context, albumId: Int) = Intent(context, AlbumDetailsActivity::class.java).apply {
            putExtra(EXTRA_ALBUM_ID, albumId)
        }
    }

    override val layoutResourceId = R.layout.activity_album_details

    // Todo: should be injected
    private val presenter = AlbumDetailsPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.feature_album)
    }

    //  Todo: should be done in BaseActivity
    override fun onResume() {
        super.onResume()
        presenter.takeView(this)

        intent.extras.also {
            var albumId = it?.getInt(EXTRA_ALBUM_ID)
            requireNotNull(albumId)
            presenter.getAlbum(albumId)
        }
    }

    // Todo: should be done in BaseActivity
    override fun onPause() {
        super.onPause()
        presenter.dropView()
    }

    override fun setAlbum(album: AlbumDomainModel) {
        albumIdTextView.text = album.id.toString()
        albumTitleTextView.text = album.title
    }
}
