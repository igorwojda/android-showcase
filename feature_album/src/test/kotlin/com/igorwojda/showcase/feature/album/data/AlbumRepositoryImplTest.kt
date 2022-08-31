package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.api.model.AlbumListJson
import com.igorwojda.showcase.feature.album.data.api.model.AlbumSearchJson
import com.igorwojda.showcase.feature.album.data.api.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.api.model.toEntity
import com.igorwojda.showcase.feature.album.data.api.response.GetAlbumInfoResponse
import com.igorwojda.showcase.feature.album.data.api.response.SearchAlbumResponse
import com.igorwojda.showcase.feature.album.data.api.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.database.AlbumDao
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class AlbumRepositoryImplTest {

    private val mockService: AlbumRetrofitService = mockk()
    private val mockAlbumDao: AlbumDao = mockk()

    private val cut = AlbumRepositoryImpl(mockService, mockAlbumDao)

    @Test
    fun `getAlbumInfo fetches AlbumInfo and maps to Model`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"

        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, null)
        } returns GetAlbumInfoResponse(DataFixtures.getAlbum())

        // when
        val actual = runBlocking { cut.getAlbumInfo(artistName, albumName, null) }

        // then
        actual shouldBeEqualTo DataFixtures.getAlbum().toDomainModel()
    }

    @Test
    fun `getAlbumInfo returns null if response is null`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"

        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, null)
        } returns null

        // when
        val actual = runBlocking { cut.getAlbumInfo(artistName, albumName, null) }

        // then
        actual shouldBeEqualTo null
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

        coEvery {
            mockAlbumDao.insertAlbums(any())
        } returns Unit

        // when
        val actual = runBlocking { cut.searchAlbum(phrase) }

        // then
        actual shouldBeEqualTo listOf(DataFixtures.getAlbum().toDomainModel())
    }

    @Test
    fun `searchAlbum return data from database if offline`() {
        // given
        val phrase = "phrase"
        val albumsJson = DataFixtures.getAlbums()
        val albumEntities = albumsJson.map { it.toEntity() }
        val albums = albumsJson.map { it.copy(wiki = null) }.map { it.toDomainModel() }

        coEvery { mockService.searchAlbumAsync(phrase) } throws UnknownHostException()
        coEvery { mockAlbumDao.getAll() } returns albumEntities

        // when
        val actual = runBlocking { cut.searchAlbum(phrase) }

        // then
        actual shouldBeEqualTo albums
    }
}
