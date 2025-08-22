package com.igorwojda.showcase.feature.base

import com.igorwojda.showcase.feature.base.presentation.nav.NavManager
import org.koin.dsl.module

val baseModule =
    module {

        single { NavManager() }
    }
