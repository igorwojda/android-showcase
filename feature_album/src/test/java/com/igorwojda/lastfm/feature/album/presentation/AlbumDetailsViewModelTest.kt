package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCaseImpl
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDetailsViewModelTest {
    companion object {
        const val ALBUM_ID = "1"
    }

    @Mock
    lateinit var mockSearchAlbumUseCase: GetAlbumUseCaseImpl

    private lateinit var cut: AlbumDetailsViewModel

    @Before
    fun setUp() {
        cut = AlbumDetailsViewModel(
            ALBUM_ID,
            mockSearchAlbumUseCase
        )
    }

    @Test
    fun `when init then getAlbumUseCase execute`() {
        runBlocking {
            // when
            cut.init()

            // then
            verify(mockSearchAlbumUseCase).execute(ALBUM_ID, artistName)
        }
    }
}
