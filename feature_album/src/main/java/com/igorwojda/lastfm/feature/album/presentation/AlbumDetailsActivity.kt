package com.igorwojda.lastfm.feature.album.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.transaction
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity

class AlbumDetailsActivity : BaseActivity() {
    companion object {
        private const val EXTRA_ALBUM_NAME = "EXTRA_ALBUM_NAME"
        private const val EXTRA_ARTIST_NAME = "EXTRA_ARTIST_NAME"

        fun getStartIntent(context: Context, artistName: String, albumName: String) =
            Intent(context, AlbumDetailsActivity::class.java).apply {
                putExtra(EXTRA_ALBUM_NAME, albumName)
                putExtra(EXTRA_ARTIST_NAME, artistName)
            }
    }

    override val layoutResourceId = R.layout.activity_album_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.feature_album)

        if (savedInstanceState == null) {
            val albumName = intent?.extras?.getString(EXTRA_ALBUM_NAME)
            require(!albumName.isNullOrEmpty()) { "albumName is null" }

            val artistName = intent?.extras?.getString(EXTRA_ARTIST_NAME)
            require(!artistName.isNullOrEmpty()) { "artistName is null" }

            supportFragmentManager.transaction {
                replace(R.id.container, AlbumDetailFragment.newInstance(albumName, albumName))
            }
        }
    }
}
