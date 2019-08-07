package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.igorwojda.base.presentation.activity.BaseActivity
import com.igorwojda.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_favourites.*

class MainActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navController = findNavController(R.id.navigationHostFragment)
        bottomNavigation.setupWithNavController(navController)
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
