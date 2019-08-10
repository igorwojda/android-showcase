package com.igorwojda.showcase.feature.album.presentation

import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailFragmentArgs
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailsViewModel
import com.igorwojda.showcase.library.testutils.CoroutineRule
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.willReturn
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDetailsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @Mock
    internal lateinit var mockSearchAlbumUseCase: GetAlbumUseCase

    @Mock
    internal lateinit var mockAlbumDetailFragmentArgs: AlbumDetailFragmentArgs

    private lateinit var cut: AlbumDetailsViewModel

    @Before
    fun setUp() {
        cut = AlbumDetailsViewModel(
            mockSearchAlbumUseCase,
            mockAlbumDetailFragmentArgs
        )
    }

    @Test
    fun `when init then getAlbumUseCase execute`() {
        runBlocking {
            // given
            val albumName = "albumName"
            val artistName = "artistName"
            val mbId = "mbId"
            given { mockAlbumDetailFragmentArgs.albumName }.willReturn { albumName }
            given { mockAlbumDetailFragmentArgs.artistName }.willReturn { artistName }
            given { mockAlbumDetailFragmentArgs.mbId }.willReturn { mbId }

            // when
            cut.loadData()

            // then
            verify(mockSearchAlbumUseCase).execute(artistName, albumName, mbId)
        }
    }
}
