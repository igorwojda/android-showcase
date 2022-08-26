package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.igorwojda.showcase.R
import com.igorwojda.showcase.base.extension.navigateSafe
import com.igorwojda.showcase.base.presentation.activity.BaseActivity
import com.igorwojda.showcase.base.presentation.navigation.NavManager
import com.igorwojda.showcase.databinding.ActivityNavHostBinding
import org.koin.android.ext.android.inject

class NavHostActivity : BaseActivity(R.layout.activity_nav_host) {

    private val binding: ActivityNavHostBinding by viewBinding()

    private val navController get() = findNavController(this, R.id.navHostFragment)

    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initNavManager()
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.bottomNav.setupWithNavController(navController)

        // TODO
        // Disables reselection of bottom menu item, so fragments are not recreated when clicking on the same menu item
        binding.bottomNav.setOnNavigationItemReselectedListener { }
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            currentFragment?.navigateSafe(it)
        }
    }
}
