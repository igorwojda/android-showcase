package com.igorwojda.showcase.base.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.showcase.base.BuildConfig
import com.igorwojda.showcase.base.presentation.ext.asLiveData
import kotlin.properties.Delegates

abstract class BaseViewModel<State : BaseState, Action : BaseAction>(initialState: State) :
    ViewModel() {

    private val stateMutableLiveData = MutableLiveData<State>()
    val stateLiveData = stateMutableLiveData.asLiveData()

    private var stateTimeTravelDebugger: StateTimeTravelDebugger? = null

    init {
        if (BuildConfig.DEBUG) {
            stateTimeTravelDebugger = StateTimeTravelDebugger(this::class.java.simpleName)
        }
    }

    // Delegate handles state event deduplication (multiple states of the same type holding the same data
    // will not be dispatched multiple times to LiveData stream)
    protected var state by Delegates.observable(initialState) { _, old, new ->
        stateMutableLiveData.postValue(new)

        // TODO needed?
        if (new != old) {
            stateTimeTravelDebugger?.apply {
                addStateTransition(old, new)
                logLast()
            }
        }
    }

    protected fun sendAction(action: Action) {
        stateTimeTravelDebugger?.addAction(action)
        state = onReduceState(action)
    }

    protected abstract fun onReduceState(action: Action): State
}
