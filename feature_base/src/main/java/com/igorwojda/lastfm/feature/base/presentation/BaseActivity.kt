package com.igorwojda.lastfm.feature.base.presentation

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
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
}
