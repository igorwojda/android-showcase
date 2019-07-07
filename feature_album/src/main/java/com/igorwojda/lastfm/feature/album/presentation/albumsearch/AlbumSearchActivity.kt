package com.igorwojda.lastfm.feature.album.presentation.albumsearch

import android.content.Context
import android.os.Bundle
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.activity.BaseContainerActivity
import com.igorwojda.lastfm.feature.base.presentation.extension.startActivity

class AlbumSearchActivity : BaseContainerActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity<AlbumSearchActivity>()
        }
    }

    override val layoutResourceId = R.layout.activity_album_list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        displayFragment { AlbumSearchFragment() }

        title = resources.getString(R.string.search_album)
    }
}
