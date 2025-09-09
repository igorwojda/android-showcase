package com.igorwojda.showcase.feature.album.presentation.screen.albumdetail

import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailViewModel.AlbumDetailUiState
import com.igorwojda.showcase.feature.base.domain.result.Result
import com.igorwojda.showcase.library.testutils.CoroutinesTestDispatcherExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class AlbumDetailViewModelTest {
    private val mockGetAlbumUseCase: GetAlbumUseCase = mockk()

    private val cut =
        AlbumDetailViewModel(
            mockGetAlbumUseCase,
        )

    @Test
    @Disabled("mockk can't correctly mock this function https://github.com/mockk/mockk/issues/957")
    fun `onInit album is not found`() =
        runTest {
            // given
            val albumName = "Thriller"
            val artistName = "Michael Jackson"
            val mbId = "123"

//            val mockAlbumDetailFragmentArgs = AlbumDetailFragmentArgs(albumName, artistName, mbId)

            // mockk Bug:
            // Exception in thread "Test worker @coroutine#4" io.mockk.MockKException: no answer found for:
            // GetAlbumUseCase(#1).execute(Thriller, Michael Jackson, 123, continuation {})
            coEvery {
                mockGetAlbumUseCase(artistName, albumName, mbId)
            } returns Result.Failure()

            // when
//            cut.onInit(mockAlbumDetailFragmentArgs)

            // then
            cut.uiStateFlow.value shouldBeEqualTo AlbumDetailUiState.Error
        }
}
