package com.igorwojda.base.presentation.activity

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.igorwojda.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.library.base.R
import timber.log.Timber

abstract class BaseContainerActivity : BaseActivity() {
    @get:LayoutRes
    override val layoutResourceId: Int = R.layout.activity_base_container

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState).also {
        setContentView(layoutResourceId)

        Timber.v("onCreate ${javaClass.simpleName}")
    }

    protected inline fun <reified T : BaseContainerFragment> displayScreenContainer(createFragment: () -> T): T {
        @IdRes val containerId = R.id.screenContainer
        return getScreenContainer(containerId) ?: createFragment().also { replaceScreenContainer(it, containerId) }
    }

    protected inline fun <reified T : BaseContainerFragment> getScreenContainer(containerId: Int): T? =
        supportFragmentManager.findFragmentById(containerId) as? T

    protected fun replaceScreenContainer(fragment: Fragment, containerId: Int) {
        supportFragmentManager.transaction { replace(containerId, fragment) }
    }
}
