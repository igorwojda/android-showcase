package com.igorwojda.showcase.base.presentation.viewmodel

import timber.log.Timber
import kotlin.reflect.full.memberProperties

/**
 * Logs actions and view state transitions to facilitate debugging.
 */
class StateTimeTravelDebugger(private val viewClassName: String) {

    private val stateTimeline = mutableListOf<StateTransition>()
    private var lastViewAction: BaseAction<*>? = null

    fun addAction(viewAction: BaseAction<*>) {
        lastViewAction = viewAction
    }

    fun addStateTransition(oldState: BaseState, newState: BaseState) {
        val lastViewAction = checkNotNull(lastViewAction) { "lastViewAction is null. Please log action before logging state transition" }
        stateTimeline.add(StateTransition(oldState, lastViewAction, newState))
        this.lastViewAction = null
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

    private fun getLogLine(oldState: BaseState, newState: BaseState, propertyName: String): String {
        val oldValue = getPropertyValue(oldState, propertyName)
        val newValue = getPropertyValue(newState, propertyName)
        val indent = "\t"

        return if (oldValue != newValue) {
            "$indent*$propertyName: $oldValue -> $newValue\n"
        } else {
            "$indent$propertyName: $newValue\n"
        }
    }

    // Get list of properties from  ViewState instances (all have the same type)
    private val propertyNames by lazy {
        stateTimeline.first().oldState.javaClass.kotlin.memberProperties.map { it.name }
    }

    private fun getPropertyValue(baseState: BaseState, propertyName: String): String {
        baseState::class.memberProperties.forEach {
            if (propertyName == it.name) {
                var value = it.getter.call(baseState).toString()

                if (value.isBlank()) {
                    value = "\"\""
                }

                return value
            }
        }
        return ""
    }

    private data class StateTransition(
        val oldState: BaseState,
        val action: BaseAction<*>,
        val newState: BaseState,
    )
}
