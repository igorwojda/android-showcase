package com.igorwojda.showcase.feature.base.presentation.viewmodel

import kotlin.reflect.full.memberProperties
import timber.log.Timber

/**
 * Logs actions and view state transitions to facilitate debugging.
 */
class StateTimeTravelDebugger(
    private val viewClassName: String,
) {
    private val stateTimeline = mutableListOf<StateTransition>()
    private var lastViewAction: BaseAction<*>? = null

    // Get list of properties from  ViewState instances (all have the same type)
    private val propertyNames by lazy {
        stateTimeline
            .first()
            .oldState.javaClass.kotlin.memberProperties
            .map { it.name }
    }

    fun addAction(viewAction: BaseAction<*>) {
        lastViewAction = viewAction
    }

    fun addStateTransition(
        oldState: BaseState,
        newState: BaseState,
    ) {
        val lastViewAction = checkNotNull(lastViewAction) { "lastViewAction is null. Please log action before logging state transition" }
        stateTimeline.add(StateTransition(oldState, lastViewAction, newState))
        this.lastViewAction = null
    }

    private fun getMessage() = getMessage(stateTimeline)

    private fun getMessage(stateTimeline: List<StateTransition>): String {
        if (stateTimeline.isEmpty()) return "$viewClassName has no state transitions\n"

        return stateTimeline.joinToString(separator = "\n", postfix = "\n") { st ->
            buildString {
                append("Action: $viewClassName.${st.action.javaClass.simpleName}")

                if (propertyNames.isNotEmpty()) {
                    append('\n')

                    append(
                        propertyNames.joinToString(separator = "") { prop ->
                            getLogLine(st.oldState, st.newState, prop)
                        },
                    )
                }
            }
        }
    }

    fun logAll() {
        Timber.d(getMessage())
    }

    fun logLast() {
        val states = listOf(stateTimeline.last())
        Timber.tag("Action").d(getMessage(states))
    }

    private fun getLogLine(
        oldState: BaseState,
        newState: BaseState,
        propertyName: String,
    ): String {
        val oldValue = getPropertyValue(oldState, propertyName)
        val newValue = getPropertyValue(newState, propertyName)
        val indent = "\t"

        return if (oldValue != newValue) {
            "$indent*$propertyName: $oldValue -> $newValue\n"
        } else {
            "$indent$propertyName: $newValue\n"
        }
    }

    private fun getPropertyValue(
        baseState: BaseState,
        propertyName: String,
    ): String {
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
