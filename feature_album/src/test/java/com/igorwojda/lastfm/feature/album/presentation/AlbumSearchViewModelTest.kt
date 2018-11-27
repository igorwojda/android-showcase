package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCaseImpl
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumSearchViewModelTest {
    @Mock
    internal lateinit var mockGetAlbumSearchUseCase: SearchAlbumUseCaseImpl

    private lateinit var cut: AlbumSearchViewModel

    @Before
    fun setUp() {
        cut = AlbumSearchViewModel(mockGetAlbumSearchUseCase)
    }

    @Test
    fun `when init then getAlbumSearchUseCase execute`() {
        runBlocking {
            // given
            val phrase = "abc"
            cut.debounceDelay = 0

            // when
            cut.searchAlbum(phrase)
            delay(4000)

            // then
            verify(mockGetAlbumSearchUseCase).execute(phrase)
        }
    }
}

