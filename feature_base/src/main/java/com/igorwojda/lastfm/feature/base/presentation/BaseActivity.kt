package com.igorwojda.lastfm.feature.base.presentation

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.igorwojda.lastfm.feature.base.R
import com.igorwojda.lastfm.feature.base.presentation.delegate.ActivityExtraDelegate
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity(), KodeinAware {
    override val kodein by closestKodein()

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState).also {
        setContentView(layoutResourceId)
        Timber.v("onCreate ${javaClass.simpleName}")
    }

    fun replaceContainer(fragment: Fragment) {
        supportFragmentManager.transaction { replace(R.id.container, fragment) }
    }

    protected inline fun <reified T : BaseFragment> displayFragment(createFragment: () -> T) =
        getContainerFragment() ?: createFragment().also { replaceContainer(it) }

    protected inline fun <reified T : BaseFragment> getContainerFragment(): T? =
        supportFragmentManager.findFragmentById(R.id.container) as? T

    protected inline fun <reified T : Any?> extra() = ActivityExtraDelegate<T>()
}
