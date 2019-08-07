package com.igorwojda.showcase.feature.favourite.domain

import com.igorwojda.showcase.feature.favourite.FEATURE_NAME
import org.kodein.di.Kodein

internal val domainModule = Kodein.Module("${FEATURE_NAME}DomainModule") { }
