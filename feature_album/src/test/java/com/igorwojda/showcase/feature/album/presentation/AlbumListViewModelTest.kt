package com.igorwojda.showcase.feature.album.presentation

import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel
import com.igorwojda.showcase.library.testutils.CoroutineRule
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
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
    internal lateinit var mockGetAlbumSearchUseCase: GetAlbumListUseCase

    private lateinit var cut: AlbumListViewModel

    @Test
    fun `when init then execute getAlbumUseCase`() {
        runBlocking {
            // given
            cut = AlbumListViewModel(mockGetAlbumSearchUseCase)

            // then
            verify(mockGetAlbumSearchUseCase).execute()
        }
    }
}
