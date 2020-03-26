package com.igorwojda.showcase.library.base.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseContainerFragment<B : ViewDataBinding> : InjectionFragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    lateinit var binding: B

    /**
     * Abstract method that all implementators must override to assign data binding variables.
     */
    protected abstract fun setupBinding(view: View)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, null, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBinding(view)
    }
}
