package com.igorwojda.showcase.feature.base.presentation.viewmodel

interface BaseAction<State> {
    fun reduce(state: State): State
}
