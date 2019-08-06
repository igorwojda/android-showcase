package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import com.igorwojda.base.presentation.activity.BaseContainerActivity
import com.igorwojda.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.R
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favourites.*

class MainActivity : BaseContainerActivity() {

    override val layoutResId = R.layout.activity_main

    private val albumSearchFragment by lazy { AlbumListFragment() }
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
    override val layoutResourceId = R.layout.fragment_favourites

    override fun onResume() {
        super.onResume()
        underConstructionAnimation.playAnimation()
    }
}

class ProfileFragment : BaseContainerFragment() {
    override val layoutResourceId = R.layout.fragment_profile

    override fun onResume() {
        super.onResume()
        underConstructionAnimation.playAnimation()
    }
}
