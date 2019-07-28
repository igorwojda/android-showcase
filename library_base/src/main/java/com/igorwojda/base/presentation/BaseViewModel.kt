package com.igorwojda.base.presentation

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    @Suppress("detekt.MagicNumber")
    var debounceDelay = 250L
}
