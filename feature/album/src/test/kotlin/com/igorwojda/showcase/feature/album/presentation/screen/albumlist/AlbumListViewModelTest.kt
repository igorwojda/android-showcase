package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import androidx.lifecycle.SavedStateHandle
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.base.domain.result.Result
import com.igorwojda.showcase.library.testutils.CoroutinesTestDispatcherExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class AlbumListViewModelTest {
    private val mockGetAlbumListUseCase: GetAlbumListUseCase = mockk()

    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)

    private val sut =
        AlbumListViewModel(
            savedStateHandle,
            mockGetAlbumListUseCase,
        )

    @Test
    fun `onInit emits state error`() =
        runTest {
            // given
            coEvery { mockGetAlbumListUseCase.invoke("Jackson") } returns Result.Failure()

            // when
            sut.onInit("Jackson")

            // then
            advanceUntilIdle()

            sut.uiStateFlow.value shouldBeEqualTo AlbumListUiState.Error
        }

    @Test
    fun `onInit emits state success`() =
        runTest {
            // given
            val album = Album("albumName", "artistName")
            val albums = listOf(album)
            coEvery { mockGetAlbumListUseCase.invoke("Jackson") } returns Result.Success(albums)

            // when
            sut.onInit("Jackson")

            // then
            advanceUntilIdle()

            sut.uiStateFlow.value shouldBeEqualTo
                AlbumListUiState.Content(
                    albums = albums,
                )
        }

    @Test
    fun `onSearch with empty query loads default query instead of saved one`() =
        runTest {
            // given
            val sut =
                AlbumListViewModel(
                    SavedStateHandle(mapOf("query" to "Metallica")),
                    mockGetAlbumListUseCase,
                )
            coEvery { mockGetAlbumListUseCase.invoke(any()) } returns Result.Success(emptyList())

            // when
            sut.onSearch("")

            // then
            advanceUntilIdle()

            coVerify { mockGetAlbumListUseCase.invoke("Jackson") }
            coVerify(exactly = 0) { mockGetAlbumListUseCase.invoke("Metallica") }
        }
}
