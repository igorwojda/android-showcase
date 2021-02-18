package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.igorwojda.showcase.R
import com.igorwojda.showcase.base.extension.viewBinding
import com.igorwojda.showcase.base.presentation.activity.BaseActivity
import com.igorwojda.showcase.base.presentation.navigation.NavManager
import com.igorwojda.showcase.databinding.ActivityNavHostBinding
import org.kodein.di.generic.instance

class NavHostActivity : BaseActivity() {

    private val binding by viewBinding(ActivityNavHostBinding::inflate)

    private val navController get() = findNavController(this, R.id.navHostFragment)

    private val navManager: NavManager by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initBottomNavigation()
        initNavManager()
    }

    private fun initBottomNavigation() {
        binding.bottomNav.setupWithNavController(navController)

        // Disables reselection of bottom menu item, so fragments are not recreated when clicking on the same menu item
        binding.bottomNav.setOnNavigationItemReselectedListener { }
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {

            val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment)
            val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)

            /*
            When trying to navigate to a destination that is not included in the current navigation graph
            the app will crash:
            java.lang.IllegalArgumentException: Navigation action/destination X cannot be found

            This happens when a (second) navigation request is triggered from a fragment that is no longer the current
            location (currentDestination) in the navController. In other words, when navigating from A to B, there is
            a moment when:
                - Fragment A is still active and showing.
                - The navigation library already changed its current location,currentDestination, to Fragment B.

            Crash happens when a second request to navigate from fragment A comes in at exactly this moment (usually due
            to extremely fast clicking or multitouch), and it uses a destination that is not included in B’s graph.
            More: https://medium.com/@ffvanderlaan/fixing-the-dreaded-is-unknown-to-this-navcontroller-68c4003824ce
             */
            if (navController.currentDestination?.id == currentFragment?.id) {
                navController.navigate(it)
            }
        }
    }
}
