package com.igorwojda.base.presentation.activity

import android.os.Bundle
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

    protected fun replaceScreenContainer(fragment: Fragment) {
        supportFragmentManager.transaction { replace(R.id.activityContainer, fragment) }
    }

    protected inline fun <reified T : BaseContainerFragment> displayInScreenContainer(createFragment: () -> T) =
        getScreenContainer() ?: createFragment().also { replaceScreenContainer(it) }

    protected inline fun <reified T : BaseContainerFragment> getScreenContainer(): T? =
        supportFragmentManager.findFragmentById(R.id.activityContainer) as? T
}
