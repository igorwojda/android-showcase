package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.igorwojda.showcase.R
import com.igorwojda.showcase.base.presentation.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBottomNavigation()
    }
    
    private fun setupBottomNavigation() {
        val navController = navigationHostFragment.findNavController()
        bottomNavigation.setupWithNavController(navController)
    }
}
