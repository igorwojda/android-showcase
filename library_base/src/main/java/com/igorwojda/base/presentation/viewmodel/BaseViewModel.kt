package com.igorwojda.base.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs

abstract class BaseViewModel : ViewModel() {

    abstract fun setArgs(args: NavArgs?)

    abstract fun loadData()
}
