package com.igorwojda.showcase.feature.album.presentation.albumdetails

import android.content.Context
import android.os.Bundle
import com.igorwojda.base.presentation.activity.BaseContainerActivity
import com.igorwojda.base.presentation.extension.extra
import com.igorwojda.base.presentation.extension.startActivity

internal class AlbumDetailsActivity : BaseContainerActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        displayInScreenContainer { AlbumDetailFragment(albumName, artistName, mbId) }
    }
}
