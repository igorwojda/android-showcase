package com.igorwojda.showcase.feature.album.data.repository

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.data.datasource.api.response.GetAlbumInfoResponse
import com.igorwojda.showcase.feature.album.data.datasource.api.response.SearchAlbumResponse
import com.igorwojda.showcase.feature.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.datasource.database.AlbumDao
import com.igorwojda.showcase.feature.album.data.mapper.AlbumMapper
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.base.data.retrofit.ApiResult
import com.igorwojda.showcase.feature.base.domain.result.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.net.UnknownHostException

class AlbumRepositoryImplTest {
    private val mockService: AlbumRetrofitService = mockk()

    private val mockAlbumDao: AlbumDao = mockk(relaxed = true)

    private val mockAlbumMapper: AlbumMapper = mockk()

    private val sut = AlbumRepositoryImpl(mockService, mockAlbumDao, mockAlbumMapper)

    @Test
    fun `searchAlbum handles api success and returns albums`() {
        // given
        val phrase = "phrase"
        val mockAlbum = mockk<Album>()

        coEvery { mockService.searchAlbumAsync(phrase) } returns
            ApiResult.Success(
                DataFixtures.ApiResponse.getSearchAlbum(),
            )

        every { mockAlbumMapper.apiToRoom(any()) } returns mockk()
        every { mockAlbumMapper.apiToDomain(any()) } returns mockAlbum

        // when
        val actual = runBlocking { sut.searchAlbum(phrase) }

        // then
        actual shouldBeEqualTo Result.Success(listOf(mockAlbum))
    }

    @Test
    fun `searchAlbum handles api success and saves album in database`() {
        // given
        val phrase = "phrase"
        coEvery { mockService.searchAlbumAsync(phrase) } returns
            ApiResult.Success(
                DataFixtures.ApiResponse.getSearchAlbum(),
            )

        every { mockAlbumMapper.apiToRoom(any()) } returns mockk()
        every { mockAlbumMapper.apiToDomain(any()) } returns mockk()

        // when
        runBlocking { sut.searchAlbum(phrase) }

        // then
        coVerify { mockAlbumDao.insertAlbums(any()) }
    }

    @Test
    fun `searchAlbum handles api exception and fallbacks to database`() {
        // given
        val phrase = "phrase"
        val albumRoomModels = DataFixtures.getAlbumsRoomModels()
        val mockAlbum1 = mockk<Album>()
        val mockAlbum2 = mockk<Album>()

        coEvery { mockService.searchAlbumAsync(phrase) } returns ApiResult.Exception(UnknownHostException())
        coEvery { mockAlbumDao.getAll() } returns albumRoomModels
        every { mockAlbumMapper.roomToDomain(albumRoomModels[0]) } returns mockAlbum1
        every { mockAlbumMapper.roomToDomain(albumRoomModels[1]) } returns mockAlbum2

        // when
        val actual = runBlocking { sut.searchAlbum(phrase) }

        // then
        actual shouldBeEqualTo Result.Success(listOf(mockAlbum1, mockAlbum2))
    }

    @Test
    fun `searchAlbum handles api error `() {
        // given
        val phrase = "phrase"

        coEvery { mockService.searchAlbumAsync(phrase) } returns mockk<ApiResult.Error<SearchAlbumResponse>>()

        // when
        val actual = runBlocking { sut.searchAlbum(phrase) }

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
        val mockAlbum = mockk<Album>()

        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, mbId)
        } returns
            ApiResult.Success(
                GetAlbumInfoResponse(album),
            )

        every { mockAlbumMapper.apiToDomain(album) } returns mockAlbum

        // when
        val actual = runBlocking { sut.getAlbumInfo(artistName, albumName, mbId) }

        // then
        actual shouldBeEqualTo Result.Success(mockAlbum)
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

        coEvery { mockAlbumDao.getAlbum(artistName, albumName, mbId) } returns mockk()
        every { mockAlbumMapper.roomToDomain(any()) } returns mockk()

        // when
        runBlocking { sut.getAlbumInfo(artistName, albumName, mbId) }

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
        val actual = runBlocking { sut.getAlbumInfo(artistName, albumName, mbId) }

        // then
        actual shouldBeEqualTo Result.Failure()
    }
}
