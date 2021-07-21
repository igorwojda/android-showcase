package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.network.model.AlbumListJson
import com.igorwojda.showcase.feature.album.data.network.model.AlbumSearchJson
import com.igorwojda.showcase.feature.album.data.network.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.network.response.GetAlbumInfoResponse
import com.igorwojda.showcase.feature.album.data.network.response.SearchAlbumResponse
import com.igorwojda.showcase.feature.album.data.network.service.AlbumRetrofitService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class AlbumRepositoryImplTest {

    @MockK
    internal lateinit var mockService: AlbumRetrofitService

//    @MockK
//    internal lateinit var mockAlbumDao: AlbumDao

    private lateinit var cut: AlbumRepositoryImpl

    private val artistName = "Michael Jackson"
    private val albumName = "Thriller"

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        cut = AlbumRepositoryImpl(mockService)
    }

    @Test
    fun `getAlbumInfo fetches AlbumInfo and maps to Model`() {
        // given
        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, null)
        } returns GetAlbumInfoResponse(DataFixtures.getAlbum())

        // when
        val result = runBlocking { cut.getAlbumInfo(artistName, albumName, null) }

        // then
        result shouldBeEqualTo DataFixtures.getAlbum().toDomainModel()
    }

    @Test
    fun `getAlbumInfo returns null if response is null`() {
        // given
        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, null)
        } returns null

        // when
        val result = runBlocking { cut.getAlbumInfo(artistName, albumName, null) }

        // then
        result shouldBeEqualTo null
    }

    @Test
    fun `searchAlbum fetches AlbumInfo and maps to Model`() {
        // given
        val phrase = "phrase"
        coEvery { mockService.searchAlbumAsync(phrase) } returns SearchAlbumResponse(
            AlbumSearchJson(
                AlbumListJson(listOf(DataFixtures.getAlbum()))
            )
        )

//        coEvery {
//            mockAlbumDao.insertAlbums(any())
//        } returns Unit

        // when
        val result = runBlocking { cut.searchAlbum(phrase) }

        // then
        result shouldBeEqualTo listOf(DataFixtures.getAlbum().toDomainModel())
    }
//
//    @Test
//    fun `searchAlbum return data from database if offline`() {
//        // given
//        val phrase = "phrase"
//        val albumsJson = DataFixtures.getAlbums()
//        val albumEntities = albumsJson.map { it.toEntity() }
//        val albums = albumsJson.map { it.copy(wiki = null) }.map { it.toDomainModel() }
//
//        coEvery { mockService.searchAlbumAsync(phrase) } throws UnknownHostException()
//        coEvery { mockAlbumDao.getAll() } returns albumEntities
//
//        // when
//        val result = runBlocking { cut.searchAlbum(phrase) }
//
//        // then
//        result shouldBeEqualTo albums
//    }
}
