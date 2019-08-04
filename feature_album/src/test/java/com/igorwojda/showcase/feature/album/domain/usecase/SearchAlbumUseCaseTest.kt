package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.feature.album.data.repository.AlbumRepositoryImpl
import com.nhaarman.mockitokotlin2.given
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
    internal lateinit var mockAlbumRepository: AlbumRepositoryImpl

    private lateinit var cut: SearchAlbumUseCase

    @Before
    fun setUp() {
        cut = SearchAlbumUseCase(mockAlbumRepository)
    }

    @Test
    fun `return list of artist`() {
        runBlocking {
            // given
            val phrase = "abc"

            given(mockAlbumRepository.searchAlbum(phrase)).willReturn(listOf())

            // when
            cut.execute(phrase)

            // then
            val result = verify(mockAlbumRepository).searchAlbum(phrase)
        }
    }
}
