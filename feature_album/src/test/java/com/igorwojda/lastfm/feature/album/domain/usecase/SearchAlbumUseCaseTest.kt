package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.data.repository.AlbumRepositoryImpl
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchAlbumUseCaseTest {
    @Mock
    lateinit var mockAlbumRepository: AlbumRepositoryImpl

    private lateinit var cut: SearchAlbumUseCaseImpl

    @Before
    fun setUp() {
        cut = SearchAlbumUseCaseImpl(mockAlbumRepository)
    }

    @Test
    fun `when execute then getAlbum`() {
        runBlocking {
            // given
            val phrase = "abc"

            // when
            cut.execute(phrase)

            // then
            verify(mockAlbumRepository).searchAlbum(phrase)
        }
    }
}
