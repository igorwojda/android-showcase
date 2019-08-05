package com.igorwojda.showcase.feature.album.domain.usecase

import com.igorwojda.showcase.feature.album.data.repository.AlbumRepositoryImpl
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumImageDomainModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqual
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
            val albums = getAlbumList()
            given(mockAlbumRepository.searchAlbum(any())).willReturn(albums)

            // when
            val result = cut.execute()

            // then
            result shouldEqual albums
        }
    }

    @Test
    fun `filter albums without images`() {
        runBlocking {
            // given
            val albumWithImage = getAlbum(listOf(AlbumDomainImageSize.EXTRA_LARGE))
            val albumWithoutImage = getAlbum(null)
            val albums = listOf(albumWithImage, albumWithoutImage)
            given(mockAlbumRepository.searchAlbum(any())).willReturn(albums)

            // when
            val result = cut.execute()

            // then
            result shouldEqual listOf(albumWithImage)
        }
    }

    private fun getAlbumList() = listOf(getAlbum(listOf(AlbumDomainImageSize.EXTRA_LARGE)))

    private fun getAlbum(imageSizes: List<AlbumDomainImageSize>?): AlbumDomainModel {
        val images = imageSizes?.map { getAlbumImage(it) } ?: listOf()
        return AlbumDomainModel("name", "artist", images, null, null)
    }

    private fun getAlbumImage(size: AlbumDomainImageSize = AlbumDomainImageSize.EXTRA_LARGE) =
        AlbumImageDomainModel("url", size)
}
