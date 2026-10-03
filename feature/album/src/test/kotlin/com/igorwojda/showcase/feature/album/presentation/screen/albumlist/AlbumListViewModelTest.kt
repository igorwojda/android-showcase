package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import androidx.lifecycle.SavedStateHandle
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.base.domain.error.AppError
import com.igorwojda.showcase.feature.base.domain.result.Result
import com.igorwojda.showcase.library.testutils.CoroutinesTestDispatcherExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestDispatcherExtension::class)
class AlbumListViewModelTest {
    private val mockGetAlbumListUseCase: GetAlbumListUseCase = mockk()

    private val savedStateHandle = SavedStateHandle()

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
            sut.onInit()

            // then
            advanceUntilIdle()

            sut.uiStateFlow.value shouldBeEqualTo AlbumListUiState.Error()
        }

    @Test
    fun `onInit emits state success`() =
        runTest {
            // given
            val album = Album("albumName", "artistName")
            val albums = listOf(album)
            coEvery { mockGetAlbumListUseCase.invoke("Jackson") } returns Result.Success(albums)

            // when
            sut.onInit()

            // then
            advanceUntilIdle()

            sut.uiStateFlow.value shouldBeEqualTo
                AlbumListUiState.Content(
                    albums = albums,
                )
        }

    @Test
    fun `onQueryChange updates query immediately`() =
        runTest {
            // when
            sut.onQueryChange("Metal")

            // then
            sut.queryFlow.value shouldBeEqualTo "Metal"
        }

    @Test
    fun `onQueryChange searches only for last query after debounce`() =
        runTest {
            // given
            coEvery { mockGetAlbumListUseCase.invoke(any()) } returns Result.Success(emptyList())
            sut.onInit()
            advanceUntilIdle()

            // when
            sut.onQueryChange("M")
            advanceTimeBy(100)
            sut.onQueryChange("Me")
            advanceTimeBy(100)
            sut.onQueryChange("Metal")
            advanceUntilIdle()

            // then
            coVerify(exactly = 0) { mockGetAlbumListUseCase.invoke("M") }
            coVerify(exactly = 0) { mockGetAlbumListUseCase.invoke("Me") }
            coVerify(exactly = 1) { mockGetAlbumListUseCase.invoke("Metal") }
        }

    @Test
    fun `onInit searches for saved query`() =
        runTest {
            // given
            val savedStateHandle = SavedStateHandle(mapOf("query" to "Metal"))
            coEvery { mockGetAlbumListUseCase.invoke("Metal") } returns Result.Success(emptyList())
            val sut = AlbumListViewModel(savedStateHandle, mockGetAlbumListUseCase)

            // when
            sut.onInit()
            advanceUntilIdle()

            // then
            sut.queryFlow.value shouldBeEqualTo "Metal"
            coVerify(exactly = 1) { mockGetAlbumListUseCase.invoke("Metal") }
        }

    @Test
    fun `onQueryChange with empty query loads default query`() =
        runTest {
            // given
            val sut =
                AlbumListViewModel(
                    SavedStateHandle(mapOf("query" to "Metallica")),
                    mockGetAlbumListUseCase,
                )
            coEvery { mockGetAlbumListUseCase.invoke(any()) } returns Result.Success(emptyList())
            sut.onInit()
            advanceUntilIdle()

            // when
            sut.onQueryChange("")

            // then
            advanceUntilIdle()

            sut.queryFlow.value shouldBeEqualTo ""
            coVerify(exactly = 1) { mockGetAlbumListUseCase.invoke("Jackson") }
        }

    @Test
    fun `retry repeats the current query and recovers`() =
        runTest {
            coEvery { mockGetAlbumListUseCase.invoke("Metal") } returnsMany
                listOf(
                    Result.Failure(AppError.Network),
                    Result.Success(emptyList()),
                )
            sut.onQueryChange("Metal")
            sut.onInit()
            advanceUntilIdle()
            sut.uiStateFlow.value shouldBeEqualTo
                AlbumListUiState.Error(
                    AppError.Network,
                )
            sut.onRetry()
            advanceUntilIdle()
            sut.uiStateFlow.value shouldBeEqualTo AlbumListUiState.Content(emptyList())
            coVerify(exactly = 2) { mockGetAlbumListUseCase.invoke("Metal") }
        }
}
