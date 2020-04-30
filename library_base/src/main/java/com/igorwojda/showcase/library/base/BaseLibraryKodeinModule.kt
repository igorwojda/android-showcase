package com.igorwojda.showcase.library.base

import com.igorwojda.showcase.library.base.navigation.navigationModule
import org.kodein.di.Kodein

internal const val FEATURE_NAME = "Base"

val baseModule = Kodein.Module("${FEATURE_NAME}Module") {
    import(navigationModule)
}
