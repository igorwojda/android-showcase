package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.igorwojda.showcase.R
import com.igorwojda.showcase.library.base.navigation.NavigationManager
import com.igorwojda.showcase.library.base.presentation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_nav_host.*
import org.kodein.di.generic.instance

class NavHostActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_nav_host

    private val navController get() = navHostFragment.findNavController()

    private val navigationManager: NavigationManager by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigation()
        initNavigationManager()
    }

    private fun initBottomNavigation() {
        bottomNav.setupWithNavController(navController)
    }

    private fun initNavigationManager() {
        navigationManager.setOnNavigationEvent {
            navController.navigate(it)
        }
    }
}
