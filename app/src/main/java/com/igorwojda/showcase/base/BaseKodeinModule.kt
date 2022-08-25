package com.igorwojda.showcase.base

import com.igorwojda.showcase.base.presentation.navigation.NavManager
import org.koin.dsl.module

internal const val MODULE_NAME = "Base"

val baseModule = module {

    single { NavManager() }
}
