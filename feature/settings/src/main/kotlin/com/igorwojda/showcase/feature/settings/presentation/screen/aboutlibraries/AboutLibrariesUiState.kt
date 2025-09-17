package com.igorwojda.showcase.feature.settings.presentation.screen.aboutlibraries

import androidx.compose.runtime.Immutable
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseState

@Immutable
internal sealed interface AboutLibrariesUiState : BaseState {
    @Immutable
    data object Content : AboutLibrariesUiState
}
