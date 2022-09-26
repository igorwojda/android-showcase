package com.igorwojda.showcase.feature.album.presentation.albumdetail

import com.igorwojda.showcase.library.testutils.CoroutinesTestDispatcherExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class AlbumDetailViewModelTest {

//    private val mockGetAlbumUseCase: GetAlbumUseCase = mockk()
//
//    private val cut = AlbumDetailViewModel(
//        mockGetAlbumUseCase
//    )
//
//    @Test
//    fun `onEnter album is not found`() = runTest {
//        // given
//        val albumName = "Thriller"
//        val artistName = "Michael Jackson"
//        val mbId = "123"
//
//        val mockAlbumDetailFragmentArgs = AlbumDetailFragmentArgs(albumName, artistName, mbId)
//
//        coEvery {
//            mockGetAlbumUseCase.execute(artistName, albumName, mbId)
//        } returns Result.Failure(Exception())
//
//        // when
//        cut.onEnter(mockAlbumDetailFragmentArgs)
//
//        // then
//        cut.uiStateFlow.value shouldBeEqualTo AlbumDetailViewModel.State(
//            isLoading = false,
//            isError = true,
//            artistName = "",
//            albumName = "",
//            coverImageUrl = ""
//        )
//    }
}
