package com.igorwojda.lastfm.feature.base.presentation

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.igorwojda.lastfm.feature.base.R
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

    inline fun <reified T : BaseFragment> displayFragment(createFragment: () -> T) =
        getFragment() ?: createFragment().also { replaceContainer(it) }

    inline fun <reified T : BaseFragment> getFragment(): T? =
        supportFragmentManager.findFragmentById(R.id.container) as? T
}
