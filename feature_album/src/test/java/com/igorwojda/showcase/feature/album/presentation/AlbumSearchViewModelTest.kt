package com.igorwojda.showcase.feature.album.presentation

import com.igorwojda.showcase.feature.album.domain.usecase.SearchAlbumUseCaseImpl
import com.igorwojda.showcase.feature.album.presentation.albumsearch.AlbumSearchViewModel
import com.igorwojda.showcase.library.testutils.CoroutineRule
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumSearchViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

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

            // then
            verify(mockGetAlbumSearchUseCase).execute(phrase)
        }
    }
}
