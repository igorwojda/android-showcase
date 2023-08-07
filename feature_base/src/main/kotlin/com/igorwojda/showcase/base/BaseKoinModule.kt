package com.igorwojda.showcase.base

import com.igorwojda.showcase.base.presentation.nav.NavManager
import org.koin.dsl.module

val baseModule = module {

    single { NavManager() }
}
