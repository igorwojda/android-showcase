package com.igorwojda.lastfm.feature.base.presentation.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.igorwojda.lastfm.feature.base.R
import com.igorwojda.lastfm.feature.base.presentation.delegate.ActivityExtraDelegate
import com.igorwojda.lastfm.feature.base.presentation.fragment.BaseContainerFragment
import timber.log.Timber

abstract class BaseContainerActivity : InjectionActivity() {
    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState).also {
        setContentView(layoutResourceId)
        Timber.v("onCreate ${javaClass.simpleName}")
    }

    fun replaceContainer(fragment: Fragment) {
        supportFragmentManager.transaction { replace(R.id.container, fragment) }
    }

    protected inline fun <reified T : BaseContainerFragment> displayFragment(createFragment: () -> T) =
        getContainerFragment() ?: createFragment().also { replaceContainer(it) }

    protected inline fun <reified T : BaseContainerFragment> getContainerFragment(): T? =
        supportFragmentManager.findFragmentById(R.id.container) as? T

    protected inline fun <reified T : Any?> extra() = ActivityExtraDelegate<T>()
}
