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
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            navController.navigate(it)
        }
    }
}
