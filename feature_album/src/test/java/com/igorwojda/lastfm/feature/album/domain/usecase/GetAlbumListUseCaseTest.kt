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
class GetAlbumListUseCaseTest {
    @Mock
    lateinit var mockAlbumNetworkRepository: AlbumNetworkRepository

    private lateinit var cut: GetAlbumListUseCase

    @Before
    fun setUp() {
        cut = GetAlbumListUseCase(mockAlbumNetworkRepository)
    }

    @Test
    fun `when execute then getAlbumList`() {
        runBlocking {
            // when
            cut.execute()

            // then
            verify(mockAlbumNetworkRepository).getAlbumList()
        }
    }
}
