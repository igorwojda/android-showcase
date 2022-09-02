package com.igorwojda.showcase.feature.album.presentation.albumdetail

import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.State
import com.igorwojda.showcase.library.testutils.CoroutinesTestDispatcherExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class AlbumDetailViewModelTest {

    private val mockGetAlbumUseCase: GetAlbumUseCase = mockk()

    private val cut = AlbumDetailViewModel(
        mockGetAlbumUseCase
    )

    @Test
    fun `onEnter album is not found`() = runTest {
        // given
        val albumName = "Thriller"
        val artistName = "Michael Jackson"
        val mbId = "123"

        val mockAlbumDetailFragmentArgs = AlbumDetailFragmentArgs(albumName, artistName, mbId)

        coEvery {
            mockGetAlbumUseCase.execute(artistName, albumName, mbId)
        } returns Result.Failure(Exception())

        // when
        cut.onEnter(mockAlbumDetailFragmentArgs)

        // then
        cut.stateFlow.collect() shouldBeEqualTo State(
            isLoading = false,
            isError = true,
            artistName = "",
            albumName = "",
            coverImageUrl = ""
        )
    }
}
