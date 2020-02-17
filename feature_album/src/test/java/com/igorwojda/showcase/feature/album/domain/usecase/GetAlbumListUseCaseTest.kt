package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.feature.album.data.repository.AlbumRepositoryImpl
import com.igorwojda.showcase.feature.album.domain.DomainFixtures
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAlbumListUseCaseTest {

    @Mock
    internal lateinit var mockAlbumRepository: AlbumRepositoryImpl

    private lateinit var cut: GetAlbumListUseCase

    @Before
    fun setUp() {
        cut = GetAlbumListUseCase(mockAlbumRepository)
    }

    @Test
    fun `return list of albums`() {
        runBlocking {
            // given
            val albums = listOf(DomainFixtures.getAlbum(), DomainFixtures.getAlbum())
            given(mockAlbumRepository.searchAlbum(any())).willReturn(albums)

            // when
            val result = cut.execute()

            // then
            result shouldBeEqualTo albums
        }
    }

    @Test
    fun `filter albums without default image`() {
        runBlocking {
            // given
            val albumWithImage = DomainFixtures.getAlbum()
            val albumWithoutImage = DomainFixtures.getAlbum(images = listOf())
            val albums = listOf(albumWithImage, albumWithoutImage)
            given(mockAlbumRepository.searchAlbum(any())).willReturn(albums)

            // when
            val result = cut.execute()

            // then
            result shouldBeEqualTo listOf(albumWithImage)
        }
    }
}
