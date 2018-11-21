package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumListUseCase
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumListViewModelTest {
    @Mock
    lateinit var mockGetAlbumListUseCase: GetAlbumListUseCase

    private lateinit var cut: AlbumListViewModel

    @Before
    fun setUp() {
        cut = AlbumListViewModel(mockGetAlbumListUseCase)
    }

    @Test
    fun `when init then getAlbumListUseCase execute`() {
        runBlocking {
            // when
            cut.init()

            // then
            verify(mockGetAlbumListUseCase).execute()
        }
    }
}

