package com.igorwojda.base.presentation.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.igorwojda.base.presentation.activity.delegate.ActivityExtraDelegate
import com.igorwojda.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.library.base.R
import timber.log.Timber

abstract class BaseContainerActivity : InjectionActivity() {
    @get:LayoutRes
    protected open val layoutResourceId: Int = R.layout.activity_base_container

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState).also {
        setContentView(layoutResourceId)
        supportActionBar?.hide()
        Timber.v("onCreate ${javaClass.simpleName}")
    }

    protected fun replaceScreenContainer(fragment: Fragment) {
        supportFragmentManager.transaction { replace(R.id.screenContainer, fragment) }
    }

    protected inline fun <reified T : BaseContainerFragment> displayContainerFragment(createFragment: () -> T) =
        getContainerFragment() ?: createFragment().also { replaceScreenContainer(it) }

    protected inline fun <reified T : BaseContainerFragment> getContainerFragment(): T? =
        supportFragmentManager.findFragmentById(R.id.screenContainer) as? T

    protected inline fun <reified T : Any?> extra() =
        ActivityExtraDelegate<T>()
}
