package com.igorwojda.showcase.app.feature

import org.kodein.di.Kodein

interface KodeinModuleProvider {

    val kodeinModule: Kodein.Module
}
