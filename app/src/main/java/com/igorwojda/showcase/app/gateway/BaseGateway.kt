package com.igorwojda.showcase.app.gateway

import org.kodein.di.Kodein

interface BaseGateway {

    val kodeinModule: Kodein.Module
}
