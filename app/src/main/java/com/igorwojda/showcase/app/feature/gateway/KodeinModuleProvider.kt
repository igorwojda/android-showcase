package com.igorwojda.showcase.app.feature.gateway

import org.kodein.di.Kodein

interface KodeinModuleProvider {

    val kodeinModule: Kodein.Module
}
