package com.igorwojda.showcase.feature.album.presentation.albumdetail

import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailViewModel
import com.igorwojda.showcase.library.testutils.CoroutinesTestDispatcherExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class AlbumDetailViewModelTest {

    private val mockGetAlbumUseCase: GetAlbumUseCase = mockk()

    private val cut = AlbumDetailViewModel(
        mockGetAlbumUseCase
    )

//    @Test
//    fun `onEnter album is not found`() = runTest {
//        // given
//        val albumName = "Thriller"
//        val artistName = "Michael Jackson"
//        val mbId = "123"
//
//        val mockAlbumDetailFragmentArgs = AlbumDetailFragmentArgs(albumName, artistName, mbId)
//
//        // mockk Bug:
//        // Exception in thread "Test worker @coroutine#4" io.mockk.MockKException: no answer found for:
//        // GetAlbumUseCase(#1).execute(Thriller, Michael Jackson, 123, continuation {})
//        coEvery {
//            mockGetAlbumUseCase.execute(artistName, albumName, mbId)
//        } returns Result.Failure()
//
//        // when
//        cut.onEnter(mockAlbumDetailFragmentArgs)
//
//        // then
//        cut.uiStateFlow.value shouldBeEqualTo UiState.Error
//    }
}
