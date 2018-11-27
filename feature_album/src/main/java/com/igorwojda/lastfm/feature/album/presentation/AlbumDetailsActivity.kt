package com.igorwojda.lastfm.feature.album.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.transaction
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity
import com.igorwojda.lastfm.feature.base.presentation.extension.getStringOrThrow

internal class AlbumDetailsActivity : BaseActivity() {
    companion object {
        private const val EXTRA_ALBUM_NAME = "EXTRA_ALBUM_NAME"
        private const val EXTRA_ARTIST_NAME = "EXTRA_ARTIST_NAME"
        private const val EXTRA_MB_ID = "EXTRA_MB_ID"

        fun getStartIntent(
            context: Context,
            artistName: String,
            albumName: String,
            mbId: String?
        ) =
            Intent(context, AlbumDetailsActivity::class.java).apply {
                putExtra(EXTRA_ARTIST_NAME, artistName)
                putExtra(EXTRA_ALBUM_NAME, albumName)
                putExtra(EXTRA_MB_ID, mbId)
            }
    }

    override val layoutResourceId = R.layout.activity_album_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val albumName = intent.extras.getStringOrThrow(EXTRA_ALBUM_NAME)
            val artistName = intent.extras.getStringOrThrow(EXTRA_ARTIST_NAME)
            val mbId = intent.extras?.getString(EXTRA_MB_ID)

            supportFragmentManager.transaction {
                replace(R.id.container, AlbumDetailFragment.newInstance(albumName, artistName, mbId))
            }
        }
    }
}
