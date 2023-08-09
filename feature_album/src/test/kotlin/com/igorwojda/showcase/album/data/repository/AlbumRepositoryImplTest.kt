package com.igorwojda.showcase.album.data.repository

import com.igorwojda.showcase.album.data.DataFixtures
import com.igorwojda.showcase.album.data.datasource.api.model.toDomainModel
import com.igorwojda.showcase.album.data.datasource.api.response.GetAlbumInfoResponse
import com.igorwojda.showcase.album.data.datasource.api.response.SearchAlbumResponse
import com.igorwojda.showcase.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.album.data.datasource.database.AlbumDao
import com.igorwojda.showcase.album.data.datasource.database.model.toDomainModel
import com.igorwojda.showcase.base.data.retrofit.ApiResult
import com.igorwojda.showcase.base.domain.result.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class AlbumRepositoryImplTest {

    private val mockService: AlbumRetrofitService = mockk()

    private val mockAlbumDao: AlbumDao = mockk(relaxed = true)

    private val cut = AlbumRepositoryImpl(mockService, mockAlbumDao)

    @Test
    fun `searchAlbum handles api success and returns albums`() {
        // given
        val phrase = "phrase"
        val albums = DataFixtures.getAlbumsApiModel()

        coEvery { mockService.searchAlbumAsync(phrase) } returns ApiResult.Success(
            DataFixtures.ApiResponse.getSearchAlbum(),
        )

        // when
        val actual = runBlocking { cut.searchAlbum(phrase) }

        // then
        val albumsDomain = albums.map { it.toDomainModel() }
        actual shouldBeEqualTo Result.Success(albumsDomain)
    }

    @Test
    fun `searchAlbum handles api success and saves album in database`() {
        // given
        val phrase = "phrase"
        coEvery { mockService.searchAlbumAsync(phrase) } returns ApiResult.Success(
            DataFixtures.ApiResponse.getSearchAlbum(),
        )

        // when
        runBlocking { cut.searchAlbum(phrase) }

        // then
        coVerify { mockAlbumDao.insertAlbums(any()) }
    }

    @Test
    fun `searchAlbum handles api exception and fallbacks to database`() {
        // given
        val phrase = "phrase"
        val albumEntities = DataFixtures.getAlbumsEntityModels()
        val albums = albumEntities.map { it.toDomainModel() }

        coEvery { mockService.searchAlbumAsync(phrase) } returns ApiResult.Exception(UnknownHostException())
        coEvery { mockAlbumDao.getAll() } returns albumEntities

        // when
        val actual = runBlocking { cut.searchAlbum(phrase) }

        // then
        actual shouldBeEqualTo Result.Success(albums)
    }

    @Test
    fun `searchAlbum handles api error `() {
        // given
        val phrase = "phrase"

        coEvery { mockService.searchAlbumAsync(phrase) } returns mockk<ApiResult.Error<SearchAlbumResponse>>()

        // when
        val actual = runBlocking { cut.searchAlbum(phrase) }

        // then
        actual shouldBeEqualTo Result.Failure()
    }

    @Test
    fun `getAlbumInfo handles api success and returns Album`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"
        val mbId = "123"
        val album = DataFixtures.getAlbumApiModel(mbId, albumName, artistName)

        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, mbId)
        } returns ApiResult.Success(
            GetAlbumInfoResponse(album),
        )

        // when
        val actual = runBlocking { cut.getAlbumInfo(artistName, albumName, mbId) }

        // then
        actual shouldBeEqualTo Result.Success(album.toDomainModel())
    }

    @Test
    fun `getAlbumInfo handles api exception and fallbacks to database`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"
        val mbId = "123"

        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, mbId)
        } returns ApiResult.Exception(UnknownHostException())

        // when
        runBlocking { cut.getAlbumInfo(artistName, albumName, mbId) }

        // then
        coVerify { mockAlbumDao.getAlbum(artistName, albumName, mbId) }
    }

    @Test
    fun `getAlbumInfo handles api error`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"
        val mbId = "123"

        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, mbId)
        } returns mockk<ApiResult.Error<GetAlbumInfoResponse>>()

        // when
        val actual = runBlocking { cut.getAlbumInfo(artistName, albumName, mbId) }

        // then
        actual shouldBeEqualTo Result.Failure()
    }
}
