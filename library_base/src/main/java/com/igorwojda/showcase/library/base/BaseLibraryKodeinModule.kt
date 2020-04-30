package com.igorwojda.showcase.library.base

import com.igorwojda.showcase.library.base.navigation.navigationModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Base"

val baseModule = Kodein.Module("${MODULE_NAME}Module") {
    import(navigationModule)
}
