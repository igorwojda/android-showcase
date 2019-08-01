package com.igorwojda.showcase.feature.album.presentation.albumsearch

import android.content.Context
import android.os.Bundle
import com.igorwojda.base.presentation.activity.BaseContainerActivity
import com.igorwojda.base.presentation.extension.startActivity
import com.igorwojda.showcase.feature.album.R

class AlbumSearchActivity : BaseContainerActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity<AlbumSearchActivity>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        displayScreenContainer { AlbumSearchFragment() }

        title = resources.getString(R.string.search_album)
    }
}
