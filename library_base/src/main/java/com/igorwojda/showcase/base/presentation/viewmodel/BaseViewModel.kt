package com.igorwojda.showcase.base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.showcase.base.presentation.extension.toLiveData

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction> : ViewModel() {

    private val viewStateMutableLiveData = MutableLiveData<ViewState>()

    val viewStateLiveData = viewStateMutableLiveData.toLiveData()

    protected var viewState: ViewState
        get() = checkNotNull(viewStateLiveData.value) { "ViewState is null" }
        private set(value) {
            viewStateMutableLiveData.postValue(value)
        }

    abstract val initialViewState: ViewState

    init {
        viewStateMutableLiveData.postValue(initialViewState)
    }

    fun loadData() {
        onLoadData()
    }

    fun sendAction(viewAction: ViewAction) {
        viewState = onReduce(viewAction)
    }

    protected open fun onLoadData() {}

    protected abstract fun onReduce(viewAction: ViewAction): ViewState
}
