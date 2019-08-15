package com.igorwojda.showcase.base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.showcase.base.presentation.extension.toLiveData

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction> : ViewModel() {

    private val viewStateMutableLiveData = MutableLiveData<ViewState>()

    val viewStateLiveData = viewStateMutableLiveData.toLiveData()

    protected val viewState
        get() = viewStateLiveData.value ?: throw RuntimeException("viewStateLiveData is null")

    abstract val initialViewState: ViewState

    init {
        viewStateMutableLiveData.postValue(initialViewState)
    }

    fun loadData() {
        onLoadData()
    }

    fun sendAction(viewAction: ViewAction) {
        updateState(viewAction)
    }

    protected open fun onLoadData() {}

    private fun updateState(viewAction: ViewAction) {
        val newState = onReduce(viewAction)
        viewStateMutableLiveData.postValue(newState)
    }

    protected abstract fun onReduce(viewAction: ViewAction): ViewState
}
