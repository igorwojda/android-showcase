package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.igorwojda.showcase.R
import com.igorwojda.showcase.base.presentation.activity.BaseActivity
import com.igorwojda.showcase.base.presentation.ext.navigateSafe
import com.igorwojda.showcase.base.presentation.nav.NavManager
import com.igorwojda.showcase.databinding.ActivityNavHostBinding
import org.koin.android.ext.android.inject

class NavHostActivity : BaseActivity(R.layout.activity_nav_host) {

    private val binding: ActivityNavHostBinding by viewBinding()
    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavManager()
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        // When using FragmentContainerView, NavController has to be retrieved by using findFragmentById() rather than
        // by using findNavController(). More:
        // https://stackoverflow.com/questions/59275009/fragmentcontainerview-using-findnavcontroller/59275182#59275182
        val navController = binding.navHostFragment.getFragment<NavHostFragment>().navController

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
