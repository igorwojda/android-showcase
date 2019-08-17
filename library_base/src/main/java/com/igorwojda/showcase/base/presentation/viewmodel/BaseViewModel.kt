package com.igorwojda.showcase.base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.library.base.BuildConfig
import com.igorwojda.showcase.base.presentation.extension.toLiveData

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction> : ViewModel() {

    private val stateMutableLiveData = MutableLiveData<ViewState>()
    val stateLiveData = stateMutableLiveData.toLiveData()
    abstract val initialState: ViewState
    private var stateTimeTravelDebugger: StateTimeTravelDebugger? = null

    init {
        if (BuildConfig.DEBUG) {
            stateTimeTravelDebugger = StateTimeTravelDebugger(this::class.java.simpleName)
        }

        stateMutableLiveData.postValue(initialState)
    }

    protected var state: ViewState
        get() {
            // Value should never be null.
            // Fix for an edge case when data is loaded from within init method of child class
            if (stateLiveData.value == null) {
                stateMutableLiveData.value = initialState
            }

            return checkNotNull(stateLiveData.value) { "ViewState is null" }
        }
        private set(value) {
            stateMutableLiveData.postValue(value)
        }

    fun sendAction(viewAction: ViewAction) {
        val oldState = state
        val newState = onReduceState(viewAction)

        stateTimeTravelDebugger?.apply {
            addStateTransition(oldState, viewAction, newState)
            logLast()
        }

        state = newState
    }

    protected open fun onLoadData() {}

    protected abstract fun onReduceState(viewAction: ViewAction): ViewState
}
