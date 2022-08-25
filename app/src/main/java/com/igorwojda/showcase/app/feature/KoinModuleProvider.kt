package com.igorwojda.showcase.app.feature

import org.koin.core.module.Module

interface KoinModuleProvider {

    val modules: List<Module>
}
