package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.feature.album.data.repository.AlbumRepositoryImpl
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAlbumUseCaseTest {

    @Mock
    internal lateinit var mockAlbumRepository: AlbumRepositoryImpl

    private lateinit var cut: GetAlbumUseCase

    @Before
    fun setUp() {
        cut = GetAlbumUseCase(mockAlbumRepository)
    }

    @Test
    fun `when execute then getAlbum`() {
        runBlocking {
            // given
            val albumName = "albumName"
            val artistName = "artistName"
            val mbId = "123"

            // when
            cut.execute(artistName, albumName, mbId)

            // then
            verify(mockAlbumRepository).getAlbumInfo(artistName, albumName, mbId)
        }
    }
}
