package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
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
        const val ALBUM_ID = 1
    }

    @Mock
    lateinit var mockGetAlbumUseCase: GetAlbumUseCase

    private lateinit var cut: AlbumDetailsViewModel

    @Before
    fun setUp() {
        cut = AlbumDetailsViewModel(
            ALBUM_ID,
            mockGetAlbumUseCase
        )
    }

    @Test
    fun `when init then getAlbumUseCase execute`() {
        runBlocking {
            // when
            cut.init()

            // then
            verify(mockGetAlbumUseCase).execute(ALBUM_ID)
        }
    }
}
