package com.igorwojda.showcase.feature.album.presentation.screen.albumdetail

import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.base.domain.result.Result
import com.igorwojda.showcase.library.testutils.CoroutinesTestDispatcherExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class AlbumDetailViewModelTest {
    private val mockGetAlbumUseCase: GetAlbumUseCase = mockk()

    private val sut =
        AlbumDetailViewModel(
            mockGetAlbumUseCase,
        )

    @Test
    fun `onInit album is not found`() =
        runTest {
            // given
            val albumName = "Thriller"
            val artistName = "Michael Jackson"
            val mbId = "123"

            coEvery {
                mockGetAlbumUseCase.invoke(artistName, albumName, mbId)
            } returns Result.Failure()

            // when
            sut.onInit(albumName, artistName, mbId)

            // then
            advanceUntilIdle()
            sut.uiStateFlow.value shouldBeEqualTo AlbumDetailUiState.Error
        }

    @Test
    fun `onInit album is found`() =
        runTest {
            // given
            val albumName = "Thriller"
            val artistName = "Michael Jackson"
            val mbId = "123"
            val album = Album(albumName, artistName, mbId)

            coEvery {
                mockGetAlbumUseCase.invoke(artistName, albumName, mbId)
            } returns Result.Success(album)

            // when
            sut.onInit(albumName, artistName, mbId)

            // then
            advanceUntilIdle()
            sut.uiStateFlow.value shouldBeEqualTo
                AlbumDetailUiState.Content(
                    albumName = albumName,
                    artistName = artistName,
                    coverImageUrl = "",
                    tracks = null,
                    tags = null,
                )
        }
}
