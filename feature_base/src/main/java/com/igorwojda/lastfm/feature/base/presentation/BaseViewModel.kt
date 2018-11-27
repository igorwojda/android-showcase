package com.igorwojda.lastfm.feature.base.presentation

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    public var debounceDelay = 250L
}
