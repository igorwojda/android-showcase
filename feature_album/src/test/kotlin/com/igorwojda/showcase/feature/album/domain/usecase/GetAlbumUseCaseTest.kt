package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.feature.album.data.repository.AlbumRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.UnknownHostException

@RunWith(JUnit4::class)
class GetAlbumUseCaseTest {

    @MockK
    internal lateinit var mockAlbumRepository: AlbumRepositoryImpl

    private lateinit var cut: GetAlbumUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        cut = GetAlbumUseCase(mockAlbumRepository)
    }

    @Test
    fun `when execute then getAlbum`() {
        // given
        val albumName = "albumName"
        val artistName = "artistName"
        val mbId = "123"

        coEvery { mockAlbumRepository.getAlbumInfo(artistName, albumName, mbId) } answers { mockk() }

        // when
        runBlocking { cut.execute(artistName, albumName, mbId) }

        // then
        coVerify { mockAlbumRepository.getAlbumInfo(artistName, albumName, mbId) }
    }

    @Test
    fun `when execute then getAlbum throw exception`() {
        // given
        val albumName = "albumName"
        val artistName = "artistName"
        val mbId = "123"
        val exception = UnknownHostException()

        coEvery { mockAlbumRepository.getAlbumInfo(artistName, albumName, mbId) } throws exception

        // when
        val result = runBlocking { cut.execute(artistName, albumName, mbId) }

        // then
        coVerify { mockAlbumRepository.getAlbumInfo(artistName, albumName, mbId) }
        result shouldBeEqualTo GetAlbumUseCase.Result.Error(exception)
    }
}
