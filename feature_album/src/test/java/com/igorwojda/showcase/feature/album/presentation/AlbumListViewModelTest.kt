package com.igorwojda.showcase.feature.album.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel.ViewState
import com.igorwojda.showcase.library.testutils.CoroutineRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    internal lateinit var mockGetAlbumSearchUseCase: GetAlbumListUseCase

    private lateinit var cut: AlbumListViewModel

    @Before
    fun setUp() {
        cut = AlbumListViewModel(mockGetAlbumSearchUseCase)
    }

    @Test
    fun `execute getAlbumUseCase`() {
        // when
        cut.loadData()

        // then
        runBlocking {
            verify(mockGetAlbumSearchUseCase).execute()
        }
    }

    @Test
    fun `verify state when GetAlbumSearchUseCase returns empty list`() {
        // given
        mockGetAlbumSearchUseCase.stub {
            onBlocking { execute() } doReturn listOf()
        }
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
    fun `verify state when GetAlbumSearchUseCase returns non-empty list`() {
        // given
        val album = AlbumDomainModel("albumName", "artistName", listOf())
        val albums = listOf(album)
        mockGetAlbumSearchUseCase.stub {
            onBlocking { execute() } doReturn albums
        }

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
