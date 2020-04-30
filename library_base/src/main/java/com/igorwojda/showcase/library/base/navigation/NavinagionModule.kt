package com.igorwojda.showcase.library.base.navigation

import com.igorwojda.showcase.library.base.MODULE_NAME
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

internal val navigationModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind() from singleton { NavigationManager() }
}
