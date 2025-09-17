package com.igorwojda.showcase.feature.settings.presentation.screen.settings

import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseAction

internal sealed class SettingsAction : BaseAction<SettingsUiState> {
    data object ShowAboutLibraries : SettingsAction() {
        override fun reduce(state: SettingsUiState) = state
    }
}
