package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.igorwojda.showcase.R
import com.igorwojda.showcase.library.base.navigation.NavigationManager
import com.igorwojda.showcase.library.base.presentation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_nav_host.*
import org.kodein.di.generic.instance

class NavHostActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_nav_host

    private val navigationManager: NavigationManager by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()

        initNavigationManager()
    }

    private fun initNavigationManager() {
        val host = navHostFragment as NavHostFragment
        val navController = host.navController

        navigationManager.setOnNavigationEvent {
            navHostFragment.findNavController().navigate(it)
        }
    }

    private fun setupBottomNavigation() {
        val navController = navHostFragment.findNavController()
        bottomNav.setupWithNavController(navController)
    }
}
