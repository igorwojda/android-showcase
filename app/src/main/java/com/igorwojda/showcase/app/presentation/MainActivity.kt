package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import com.igorwojda.base.presentation.activity.BaseContainerActivity
import com.igorwojda.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.R
import com.igorwojda.showcase.app.gateway.AlbumGateway
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_common1.*
import org.kodein.di.generic.instance

class MainActivity : BaseContainerActivity() {

    override val layoutResId = R.layout.activity_main

    private val albumGateway: AlbumGateway by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()

        if (savedInstanceState == null) {
            replaceScreenContent(albumGateway.createAlbumSearchFragment())
        }
    }

    private fun setupBottomNavigation() {
        val selectItem = true

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottomMenuHome -> {
                    replaceScreenContent(BlogFragment())
                    selectItem
                }
                R.id.bottomMenuFavourites -> {
                    replaceScreenContent(ChapterFragment())
                    selectItem
                }
                R.id.bottomMenuProfile -> {
                    replaceScreenContent(StoreFragment())
                    selectItem
                }
                else -> {
                    !selectItem
                }
            }
        }
    }
}

class BlogFragment : BaseContainerFragment() {
    override val layoutResourceId = R.layout.fragment_common1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvCommon.text = "Store Fragment"
        commonLayout.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
    }
}


class ChapterFragment : BaseContainerFragment() {
    override val layoutResourceId = R.layout.fragment_common1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        tvCommon.text = "Chapter Fragment"
        commonLayout.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))

        super.onActivityCreated(savedInstanceState)
    }
}


class StoreFragment : BaseContainerFragment() {
    override val layoutResourceId = R.layout.fragment_common1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvCommon.text = "Blog Fragment"
        commonLayout.setBackgroundColor(resources.getColor(android.R.color.holo_orange_dark))

    }
}
