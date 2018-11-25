package com.igorwojda.lastfm.feature.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import timber.log.Timber

abstract class BaseFragment : Fragment(), LifecycleOwner, KodeinAware {
    override val kodein by closestKodein()

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutResourceId, null).also {
            Timber.v("onCreateView ${javaClass.simpleName}")
        }
}
