package com.igorwojda.showcase.feature.profile.domain

import com.igorwojda.showcase.feature.profile.FEATURE_NAME
import org.kodein.di.Kodein

internal val domainModule = Kodein.Module("${FEATURE_NAME}DomainModule") { }
