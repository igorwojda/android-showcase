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

    private val albumSearchFragment by lazy { albumGateway.createAlbumSearchFragment() }
    private val favouritesFragment by lazy { FavouritesFragment() }
    private val profileFragment by lazy { ProfileFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()

        if (savedInstanceState == null) {
            bottomNavigation.selectedItemId = R.id.bottomMenuHome
        }
    }

    private fun setupBottomNavigation() {
        val selectItem = true

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottomMenuHome -> {
                    replaceScreenContent(albumSearchFragment)
                    selectItem
                }
                R.id.bottomMenuFavourites -> {
                    replaceScreenContent(favouritesFragment)
                    selectItem
                }
                R.id.bottomMenuProfile -> {
                    replaceScreenContent(profileFragment)
                    selectItem
                }
                else -> {
                    !selectItem
                }
            }
        }
    }
}


class FavouritesFragment : BaseContainerFragment() {
    override val layoutResourceId = R.layout.fragment_common1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        tvCommon.text = "Favourites Fragment"
        commonLayout.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))

        super.onActivityCreated(savedInstanceState)
    }
}


class ProfileFragment : BaseContainerFragment() {
    override val layoutResourceId = R.layout.fragment_common1

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvCommon.text = "Profile Fragment"
        commonLayout.setBackgroundColor(resources.getColor(android.R.color.holo_orange_dark))

    }
}
