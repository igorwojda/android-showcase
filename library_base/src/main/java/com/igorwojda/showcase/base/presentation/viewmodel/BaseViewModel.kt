package com.igorwojda.showcase.base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.library.base.BuildConfig
import com.igorwojda.showcase.base.delegate.observer
import com.igorwojda.showcase.base.presentation.extension.toLiveData

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction> : ViewModel() {

    private val viewStateMutableLiveData = MutableLiveData<ViewState>()

    val viewStateLiveData = viewStateMutableLiveData.toLiveData()

    private val stateTimeline = mutableListOf<Triple<ViewState, ViewAction, ViewState>>()

    abstract val initialViewState: ViewState

    protected var viewState: ViewState by observer(initialViewState) {
        viewStateMutableLiveData.postValue(it)
    }

    fun loadData() {
        onLoadData()
    }

    fun sendAction(viewAction: ViewAction) {
        val oldState = viewState
        val newState = onReduce(viewAction)

        if (BuildConfig.DEBUG) {
            stateTimeline.add(Triple(oldState, viewAction, newState))
        }

        viewState = newState
    }

    protected open fun onLoadData() {}

    protected abstract fun onReduce(viewAction: ViewAction): ViewState
}
