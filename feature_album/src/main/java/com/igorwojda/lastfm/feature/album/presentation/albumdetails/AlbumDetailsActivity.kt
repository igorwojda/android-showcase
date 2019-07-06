package com.igorwojda.lastfm.feature.album.presentation.albumdetails

import android.content.Context
import android.os.Bundle
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.base.presentation.BaseActivity
import com.igorwojda.lastfm.feature.base.presentation.extension.startActivity

internal class AlbumDetailsActivity : BaseActivity() {

    private val albumName by extra<String>()
    private val artistName by extra<String>()
    private val mbId by extra<String?>()

    companion object {

        fun start(
            context: Context,
            artistName: String,
            albumName: String,
            mbId: String?
        ) {
            // A companion object property references of containing class members must be prefixed with class name
            // See: https://youtrack.jetbrains.com/issue/KT-9315
            context.startActivity<AlbumDetailsActivity>(
                AlbumDetailsActivity::albumName to albumName,
                AlbumDetailsActivity::artistName to artistName,
                AlbumDetailsActivity::mbId to mbId
            )
        }
    }

    override val layoutResourceId = R.layout.activity_album_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        displayFragment { AlbumDetailFragment(albumName, artistName, mbId) }
    }
}
