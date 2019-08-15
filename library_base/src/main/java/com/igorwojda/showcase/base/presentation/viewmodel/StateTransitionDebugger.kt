package com.igorwojda.showcase.base.presentation.viewmodel

import timber.log.Timber
import kotlin.reflect.full.memberProperties

// Class responsible for logging ViewState transitions
class StateTransitionDebugger(private val viewClassName: String) {

    private val stateTimeline = mutableListOf<StateTransition>()

    fun addStateTransition(oldState: BaseViewState, viewAction: BaseAction, newState: BaseViewState) {
        stateTimeline.add(StateTransition(oldState, viewAction, newState))
    }

    private fun getMessage() = getMessage(stateTimeline)

    private fun getMessage(stateTimeline: List<StateTransition>): String {
        if (stateTimeline.isEmpty()) {
            return "$viewClassName has no state transitions \n"
        }

        var message = ""

        stateTimeline.forEach { stateTransition ->
            message += "Action: $viewClassName.${stateTransition.action.javaClass.simpleName}:\n"

            propertyNames.forEach { propertyName ->
                val logLine = getLogLine(stateTransition.oldState, stateTransition.newState, propertyName)
                message += logLine
            }
        }

        return message
    }

    fun logAll() {
        Timber.d(getMessage())
    }

    fun logLast() {
        val states = listOf(stateTimeline.last())
        Timber.d(getMessage(states))
    }

    private fun getLogLine(oldState: BaseViewState, newState: BaseViewState, propertyName: String): String {
        val oldValue = getPropertyValue(oldState, propertyName)
        val newValue = getPropertyValue(newState, propertyName)

        return if (oldValue != newValue) {
            "\t*$propertyName: $oldValue -> $newValue\n"
        } else {
            "\t$propertyName: $newValue\n"
        }
    }

    // Retrieve list of the properties from one of the ViewState instances (all have the same type)
    private val propertyNames by lazy {
        stateTimeline.first().oldState.javaClass.kotlin.memberProperties.map { it.name }
    }

    private fun getPropertyValue(baseViewState: BaseViewState, propertyName: String): String {
        baseViewState::class.memberProperties.forEach { kCallable ->
            if (propertyName == kCallable.name) {
                var value = kCallable.getter.call(baseViewState).toString()

                if (value.isBlank()) {
                    value = "\"\""
                }

                return value
            }
        }
        return ""
    }

    private data class StateTransition(
        val oldState: BaseViewState,
        val action: BaseAction,
        val newState: BaseViewState
    )
}
