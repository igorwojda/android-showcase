package com.igorwojda.base.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.igorwojda.base.presentation.viewmodel.BaseViewModel
import navArgsReflection
import timber.log.Timber


abstract class BaseContainerFragment : InjectionFragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    protected abstract val viewModel: BaseViewModel

    private val navArgs by navArgsReflection()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutResourceId, null).also {
            Timber.v("onCreateView ${javaClass.simpleName}")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.setAgrs(navArgs)
            viewModel.loadData()
        }
    }
}




