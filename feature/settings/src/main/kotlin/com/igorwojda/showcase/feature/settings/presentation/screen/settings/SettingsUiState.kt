package com.igorwojda.showcase.feature.settings.presentation.screen.settings

import androidx.compose.runtime.Immutable
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseState

@Immutable
internal sealed interface SettingsUiState : BaseState {
    @Immutable
    data object Content : SettingsUiState

    @Immutable
    data object Loading : SettingsUiState
}
