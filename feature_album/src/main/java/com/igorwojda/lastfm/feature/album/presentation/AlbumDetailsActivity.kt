package com.igorwojda.lastfm.feature.album.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.transaction
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity

class AlbumDetailsActivity : BaseActivity() {
    companion object {
        private const val EXTRA_ALBUM_ID = "EXTRA_ALBUM_ID"

        fun getStartIntent(context: Context, albumId: Int) =
            Intent(context, AlbumDetailsActivity::class.java).apply {
                putExtra(EXTRA_ALBUM_ID, albumId)
            }
    }

    override val layoutResourceId = R.layout.activity_album_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = resources.getString(R.string.feature_album)

        if (savedInstanceState != null) {
            val albumId = intent.getIntExtra(EXTRA_ALBUM_ID, 0)
            supportFragmentManager.transaction {
                replace(R.id.container, AlbumDetailFragment.newInstance(albumId))
            }
        }
    }
}
