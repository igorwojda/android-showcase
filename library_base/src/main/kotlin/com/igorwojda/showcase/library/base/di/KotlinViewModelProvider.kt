package com.igorwojda.showcase.library.base.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class KotlinViewModelProvider private constructor() {
    companion object {
        inline fun <reified T : ViewModel> of(fragment: Fragment, crossinline factory: () -> T): T {
            @Suppress("UNCHECKED_CAST")
            val vmFactory = object : ViewModelProvider.Factory {
                override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
            }

            return ViewModelProviders.of(fragment, vmFactory)[T::class.java]
        }

        inline fun <reified T : ViewModel> of(fragmentActivity: FragmentActivity, crossinline factory: () -> T): T {
            @Suppress("UNCHECKED_CAST")
            val vmFactory = object : ViewModelProvider.Factory {
                override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
            }

            return ViewModelProviders.of(fragmentActivity, vmFactory)[T::class.java]
        }
    }
}
