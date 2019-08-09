package com.igorwojda.showcase.base.presentation.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    fun loadData() {
        onLoadData()
    }

    protected open fun onLoadData() {}
}
