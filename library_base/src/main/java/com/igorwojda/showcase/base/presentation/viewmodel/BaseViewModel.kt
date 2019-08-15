package com.igorwojda.showcase.base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.library.base.BuildConfig
import com.igorwojda.showcase.base.presentation.extension.toLiveData

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction> : ViewModel() {

    private val viewStateMutableLiveData = MutableLiveData<ViewState>()
    val viewStateLiveData = viewStateMutableLiveData.toLiveData()
    abstract val initialViewState: ViewState
    private var stateTransitionDebugger: StateTransitionDebugger? = null

    init {
        if (BuildConfig.DEBUG) {
            stateTransitionDebugger = StateTransitionDebugger(this::class.java.simpleName)
        }

        viewStateMutableLiveData.postValue(initialViewState)
    }

    protected var viewState: ViewState
        get() {
            // Value should never be null.
            // Fix for an edge case when data is loaded from within init method of child class
            if (viewStateLiveData.value == null) {
                viewStateMutableLiveData.value = initialViewState
            }

            return checkNotNull(viewStateLiveData.value) { "ViewState is null" }
        }
        private set(value) {
            viewStateMutableLiveData.postValue(value)
        }

    fun loadData() {
        onLoadData()
    }

    fun sendAction(viewAction: ViewAction) {
        val oldState = viewState
        val newState = onReduce(viewAction)

        stateTransitionDebugger?.apply {
            addStateTransition(oldState, viewAction, newState)
            logLast()
        }

        viewState = newState
    }

    protected open fun onLoadData() {}

    protected abstract fun onReduce(viewAction: ViewAction): ViewState
}
