package com.igorwojda.showcase.library.base.navigation

import androidx.navigation.NavDirections

class NavigationManager {
    private var navigationEventListener: ((navDirections: NavDirections) -> Unit)? = null

    fun navigate(navDirections: NavDirections) {
        navigationEventListener?.invoke(navDirections)
    }

    fun setOnNavigationEvent(navigationEventListener: (navDirections: NavDirections) -> Unit) {
        this.navigationEventListener = navigationEventListener
    }
}
