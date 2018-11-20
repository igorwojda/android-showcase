package com.igorwojda.lastfm.feature.album.domain.usecase

import com.igorwojda.lastfm.feature.album.data.repository.AlbumNetworkRepository
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
    lateinit var mockAlbumNetworkRepository: AlbumNetworkRepository

    private lateinit var cut: GetAlbumUseCase

    @Before
    fun setUp() {
        cut = GetAlbumUseCase(mockAlbumNetworkRepository)
    }

    @Test
    fun `when execute then getAlbum`() {
        runBlocking {
            // given
            val albumId = 1

            // when
            cut.execute(albumId)

            // then
            verify(mockAlbumNetworkRepository).getAlbum(albumId)
        }
    }
}
