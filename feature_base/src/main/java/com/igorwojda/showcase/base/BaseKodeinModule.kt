package com.igorwojda.showcase.base

import com.igorwojda.showcase.base.presentation.navigation.NavManager
import org.koin.dsl.module

val baseModule = module {

    single { NavManager() }
}

