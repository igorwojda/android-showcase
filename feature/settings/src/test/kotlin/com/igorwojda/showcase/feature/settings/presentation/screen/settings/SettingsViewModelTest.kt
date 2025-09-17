package com.igorwojda.showcase.feature.settings.presentation.screen.settings

import com.igorwojda.showcase.library.testutils.CoroutinesTestDispatcherExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class SettingsViewModelTest {
    private val sut = SettingsViewModel()

    @Test
    fun `initial state should be Content`() =
        runTest {
            // then
            sut.uiStateFlow.value shouldBeEqualTo SettingsUiState.Content
        }
}
