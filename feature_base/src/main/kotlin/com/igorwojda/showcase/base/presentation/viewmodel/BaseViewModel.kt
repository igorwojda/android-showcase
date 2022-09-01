package com.igorwojda.showcase.base.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.BuildConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

abstract class BaseViewModel<State : BaseState, Action : BaseAction>(initialState: State) :
    ViewModel() {

    private val _stateFlow = MutableStateFlow(initialState)
    val stateFlow = _stateFlow.asStateFlow()

    private var stateTimeTravelDebugger: StateTimeTravelDebugger? = null

    init {
        if (BuildConfig.DEBUG) {
            stateTimeTravelDebugger = StateTimeTravelDebugger(this::class.java.simpleName)
        }
    }

    // Delegate handles state event deduplication (multiple states of the same type holding the same data
    // will not be dispatched multiple times to LiveData stream)
    protected var state by Delegates.observable(initialState) { _, old, new ->
        viewModelScope.launch {
            _stateFlow.emit(new)
        }

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
