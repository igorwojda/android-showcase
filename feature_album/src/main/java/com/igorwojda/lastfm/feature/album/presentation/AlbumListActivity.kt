package com.igorwojda.lastfm.feature.album.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.transaction
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity

class AlbumListActivity : BaseActivity() {
    companion object {
        fun newIntent(context: Context) = Intent(context, AlbumListActivity::class.java)
    }

    override val layoutResourceId = R.layout.activity_album_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            supportFragmentManager.transaction {
                replace(R.id.container, AlbumListFragment.newInstance())
            }
        }

        title = resources.getString(R.string.feature_album)
    }
}
