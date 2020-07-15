package com.igorwojda.showcase.feature.album.presentation.albumlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel.ViewState
import com.igorwojda.showcase.library.base.presentation.navigation.NavManager
import com.igorwojda.showcase.library.testutils.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AlbumListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    internal lateinit var mockGetAlbumListUseCase: GetAlbumListUseCase

    @MockK(relaxed = true)
    internal lateinit var mockNavManager: NavManager

    private lateinit var cut: AlbumListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = AlbumListViewModel(
            mockNavManager,
            mockGetAlbumListUseCase
        )
    }

    @Test
    fun `execute getAlbumUseCase`() {
        // when
        cut.loadData()

        // then
        coVerify { mockGetAlbumListUseCase.execute() }
    }

    @Test
    fun `navigate to album details`() {
        // given
        val artistName = "artistName"
        val albumName = "albumName"
        val mbId = "mbId"

        val navDirections = AlbumListFragmentDirections.actionAlbumListToAlbumDetail(
            artistName,
            albumName,
            mbId
        )

        // when
        cut.navigateToAlbumDetails(artistName, albumName, mbId)

        // then
        coVerify { mockNavManager.navigate(navDirections) }
    }

    @Test
    fun `verify state when GetAlbumListUseCase returns empty list`() {
        // given
        coEvery { mockGetAlbumListUseCase.execute() } returns GetAlbumListUseCase.Result.Success(emptyList())

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo ViewState(
            isLoading = false,
            isError = true,
            albums = listOf()
        )
    }

    @Test
    fun `verify state when GetAlbumListUseCase returns non-empty list`() {
        // given
        val album = AlbumDomainModel("albumName", "artistName", listOf())
        val albums = listOf(album)
        coEvery { mockGetAlbumListUseCase.execute() } returns GetAlbumListUseCase.Result.Success(albums)

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo ViewState(
            isLoading = false,
            isError = false,
            albums = albums
        )
    }
}
