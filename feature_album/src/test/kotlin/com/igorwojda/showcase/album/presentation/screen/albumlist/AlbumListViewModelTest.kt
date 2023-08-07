package com.igorwojda.showcase.album.presentation.screen.albumlist

import androidx.lifecycle.SavedStateHandle
import com.igorwojda.showcase.album.domain.model.Album
import com.igorwojda.showcase.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.album.presentation.screen.albumlist.AlbumListViewModel.UiState
import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.base.presentation.nav.NavManager
import com.igorwojda.showcase.testutils.CoroutinesTestDispatcherExtension
import com.igorwojda.showcase.testutils.InstantTaskExecutorExtension
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

    private val mockNavManager: NavManager = mockk(relaxed = true)

    private val savedStateHandle: SavedStateHandle = mockk(relaxed = true)

    private val cut = AlbumListViewModel(
        savedStateHandle,
        mockNavManager,
        mockGetAlbumListUseCase,
    )

    @Test
    fun `onEnter emits state error`() = runTest {
        // given
        coEvery { mockGetAlbumListUseCase.invoke(null) } returns Result.Success(emptyList())

        // when
        cut.onEnter(null)

        // then
        advanceUntilIdle()

        cut.uiStateFlow.value shouldBeEqualTo UiState.Error
    }

    @Test
    fun `onEnter emits state success`() = runTest {
        // given
        val album = Album("albumName", "artistName")
        val albums = listOf(album)
        coEvery { mockGetAlbumListUseCase.invoke(null) } returns Result.Success(albums)

        // when
        cut.onEnter(null)

        // then
        advanceUntilIdle()

        cut.uiStateFlow.value shouldBeEqualTo UiState.Content(
            albums = albums,
        )
    }

    @Test
    fun `onAlbumClick navigate to album detail`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"
        val mbId = "mbId"

        val album = Album(
            artist = artistName,
            name = albumName,
            mbId = mbId,
        )

        val navDirections = AlbumListFragmentDirections.actionAlbumListToAlbumDetail(
            artistName,
            albumName,
            mbId,
        )

        // when
        cut.onAlbumClick(album)

        // then
        coVerify { mockNavManager.navigate(navDirections) }
    }
}
