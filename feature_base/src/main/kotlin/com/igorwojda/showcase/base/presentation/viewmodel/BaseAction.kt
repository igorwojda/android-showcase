package com.igorwojda.showcase.base.presentation.viewmodel

interface BaseAction<State> {
    fun reduce(state: State): State
}
