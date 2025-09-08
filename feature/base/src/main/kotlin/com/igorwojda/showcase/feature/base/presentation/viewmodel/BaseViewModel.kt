package com.igorwojda.showcase.feature.base.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.igorwojda.showcase.feature.base.BuildConfig
import kotlin.properties.Delegates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

abstract class BaseViewModel<State : BaseState, Action : BaseAction<State>>(
    initialState: State,
) : ViewModel() {
    private val _uiStateFlow = MutableStateFlow(initialState)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    private var stateTimeTravelDebugger: StateTimeTravelDebugger? = null

    init {
        if (BuildConfig.DEBUG) {
            stateTimeTravelDebugger = StateTimeTravelDebugger(this::class.java.simpleName)
        }
    }

    // Delegate handles state event deduplication (multiple states of the same type holding the same data
    // will not be emitted multiple times to UI)
    private var state by Delegates.observable(initialState) { _, old, new ->
        Timber.d("AAA, BaseViewModel Delegates.observable, old: $old, new: $new")
        Timber.d("AAA, BaseViewModel equality check: old == new is ${old == new}")
        
        if (old != new) {
            Timber.d("AAA, BaseViewModel updating state flow directly, old: $old, new: $new")
            _uiStateFlow.value = new
            Timber.d("AAA, BaseViewModel state flow value after update: ${_uiStateFlow.value}")
            
            stateTimeTravelDebugger?.apply {
                addStateTransition(old, new)
                logLast()
            }
        } else {
            Timber.d("AAA, BaseViewModel states are equal, skipping update")
        }
    }

    protected fun sendAction(action: Action) {
        Timber.d("AAA, sendAction, action: $action")
        stateTimeTravelDebugger?.addAction(action)
        state = action.reduce(state)
    }
}
