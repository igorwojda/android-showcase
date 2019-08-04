package com.igorwojda.showcase.feature.album.presentation

import com.igorwojda.showcase.feature.album.domain.usecase.SearchAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel
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
class AlbumListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @Mock
    internal lateinit var mockGetAlbumSearchUseCase: SearchAlbumUseCase

    private lateinit var cut: AlbumListViewModel

    @Before
    fun setUp() {
        cut = AlbumListViewModel(mockGetAlbumSearchUseCase)
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
