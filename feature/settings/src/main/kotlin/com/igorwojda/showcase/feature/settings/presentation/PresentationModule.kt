package com.igorwojda.showcase.feature.settings.presentation

import com.igorwojda.showcase.feature.settings.presentation.screen.aboutlibraries.AboutLibrariesViewModel
import com.igorwojda.showcase.feature.settings.presentation.screen.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val presentationModule =
    module {
        viewModelOf(::SettingsViewModel)
        viewModelOf(::AboutLibrariesViewModel)
    }
