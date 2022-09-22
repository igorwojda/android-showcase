package com.igorwojda.showcase.base.presentation.viewmodel

interface BaseAction<State> {
    fun reduceState(state: State): State
}
