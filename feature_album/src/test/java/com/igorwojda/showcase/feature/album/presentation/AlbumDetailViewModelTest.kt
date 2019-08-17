package com.igorwojda.showcase.feature.album.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailFragmentArgs
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.ViewState
import com.igorwojda.showcase.library.testutils.CoroutineRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.any
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDetailViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    internal lateinit var mockGetAlbumUseCase: GetAlbumUseCase

    @Mock
    internal lateinit var mockAlbumDetailFragmentArgs: AlbumDetailFragmentArgs

    private lateinit var cut: AlbumDetailViewModel

    @Before
    fun setUp() {
        cut = AlbumDetailViewModel(
            mockGetAlbumUseCase,
            mockAlbumDetailFragmentArgs
        )
    }

    @Test
    fun `verify state when GetAlbumUseCase return album`() {
        // given
        val album = AlbumDomainModel("albumName", "artistName", listOf())

        mockGetAlbumUseCase.stub {
            onBlocking { execute(any(), any(), any()) } doReturn (album)
        }

        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldEqual ViewState(
            isLoading = false,
            isError = false,
            artistName = album.artist,
            albumName = album.name
            // TODO: coverImageUrl
        )
    }

    @Test
    fun `verify state when GetAlbumUseCase return null`() {
        // when
        cut.loadData()

        // then
        cut.stateLiveData.value shouldEqual ViewState(
            isLoading = false,
            isError = true,
            artistName = "",
            albumName = "",
            coverImageUrl = ""
        )
    }
}
