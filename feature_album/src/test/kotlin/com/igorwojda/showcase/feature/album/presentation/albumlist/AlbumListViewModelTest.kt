package com.igorwojda.showcase.feature.album.presentation.albumlist

import com.igorwojda.showcase.base.presentation.navigation.NavManager
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel.State
import com.igorwojda.showcase.library.testutils.CoroutinesTestExtension
import com.igorwojda.showcase.library.testutils.InstantTaskExecutorExtension
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class AlbumListViewModelTest {

    @ExperimentalCoroutinesApi
    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @JvmField
    @RegisterExtension
    var instantTaskExecutorExtension = InstantTaskExecutorExtension()

    @MockK
    internal lateinit var mockGetAlbumListUseCase: GetAlbumListUseCase

    @MockK(relaxed = true)
    internal lateinit var mockNavManager: NavManager

    private lateinit var cut: AlbumListViewModel

    @BeforeEach
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
        val artistName = "Michael Jackson"
        val albumName = "Thriller"
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
        cut.stateLiveData.value shouldBeEqualTo State(
            isLoading = false,
            isError = true,
            albums = listOf()
        )
    }

    @Test
    fun `verify state when GetAlbumListUseCase returns non-empty list`() {
        // given
        val album = Album("albumName", "artistName", listOf())
        val albums = listOf(album)
        coEvery { mockGetAlbumListUseCase.execute() } returns GetAlbumListUseCase.Result.Success(albums)

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldBeEqualTo State(
            isLoading = false,
            isError = false,
            albums = albums
        )
    }
}
