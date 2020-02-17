package com.igorwojda.showcase.feature.album.data.repository

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.data.model.AlbumListDataModel
import com.igorwojda.showcase.feature.album.data.model.AlbumSearchResultDataModel
import com.igorwojda.showcase.feature.album.data.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.retrofit.response.GetAlbumInfoResponse
import com.igorwojda.showcase.feature.album.data.retrofit.response.SearchAlbumResponse
import com.igorwojda.showcase.feature.album.data.retrofit.service.AlbumRetrofitService
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumRepositoryImplTest {

    @Mock
    internal lateinit var mockService: AlbumRetrofitService

    private lateinit var cut: AlbumRepositoryImpl

    private val artistName = "artistName"
    private val albumName = "albumName"

    @Before
    fun setUp() {
        cut = AlbumRepositoryImpl(mockService)
    }

    @Test
    fun `getAlbumInfo fetches AlbumInfo and maps to Model`() {
        runBlocking {
            // given
            given(mockService.getAlbumInfoAsync(artistName, albumName, null))
                .willReturn(GetAlbumInfoResponse(DataFixtures.getAlbum()))

            // when
            val result = cut.getAlbumInfo(artistName, albumName, null)

            // then
            result shouldBeEqualTo DataFixtures.getAlbum().toDomainModel()
        }
    }

    @Test
    fun `getAlbumInfo returns null if response is null`() {
        runBlocking {
            // given
            given(mockService.getAlbumInfoAsync(artistName, albumName, null))
                .willReturn(null)

            // when
            val result = cut.getAlbumInfo(artistName, albumName, null)

            // then
            result shouldBeEqualTo null
        }
    }

    @Test
    fun `searchAlbum fetches AlbumInfo and maps to Model`() {
        runBlocking {
            // given
            val phrase = "phrase"
            given(mockService.searchAlbumAsync(phrase)).willReturn(
                SearchAlbumResponse(
                    AlbumSearchResultDataModel(
                        AlbumListDataModel(listOf(DataFixtures.getAlbum()))
                    )
                )
            )

            // when
            val result = cut.searchAlbum(phrase)

            // then
            result shouldBeEqualTo listOf(DataFixtures.getAlbum().toDomainModel())
        }
    }
}
