package com.igorwojda.showcase.feature.album.data.repository

import com.igorwojda.showcase.base.data.retrofit.ApiResult
import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumListJson
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumSearchJson
import com.igorwojda.showcase.feature.album.data.datasource.api.model.toDomainModel
import com.igorwojda.showcase.feature.album.data.datasource.api.model.toEntity
import com.igorwojda.showcase.feature.album.data.datasource.api.response.GetAlbumInfoResponse
import com.igorwojda.showcase.feature.album.data.datasource.api.response.SearchAlbumResponse
import com.igorwojda.showcase.feature.album.data.datasource.api.service.AlbumRetrofitService
import com.igorwojda.showcase.feature.album.data.datasource.database.AlbumDao
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
        val albums = listOf(DataFixtures.getAlbum())
        coEvery { mockService.searchAlbumAsync(phrase) } returns ApiResult.Success(
            SearchAlbumResponse(
                AlbumSearchJson(
                    AlbumListJson(albums)
                )
            )
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
        val albums = listOf(DataFixtures.getAlbum())
        coEvery { mockService.searchAlbumAsync(phrase) } returns ApiResult.Success(
            SearchAlbumResponse(
                AlbumSearchJson(
                    AlbumListJson(albums)
                )
            )
        )

        // when
        val actual = runBlocking { cut.searchAlbum(phrase) }

        // then
        coVerify { mockAlbumDao.insertAlbums(any()) }
    }

    @Test
    fun `searchAlbum handles api exception and fallbacks to database`() {
        // given
        val phrase = "phrase"
        val albumsJson = DataFixtures.getAlbums()
        val albumEntities = albumsJson.map { it.toEntity() }
        val albums = albumsJson.map { it.copy(wiki = null) }.map { it.toDomainModel() }

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

        //then
        actual shouldBeEqualTo Result.Failure()
    }

    @Test
    fun `getAlbumInfo handles api success and returns Album`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"

        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, null)
        } returns ApiResult.Success(
            GetAlbumInfoResponse(
                DataFixtures.getAlbum()
            )
        )

        // when
        val actual = runBlocking { cut.getAlbumInfo(artistName, albumName, null) }

        // then
        actual shouldBeEqualTo DataFixtures.getAlbum().toDomainModel()
    }

    @Test
    fun `getAlbumInfo handles api exception and fallbacks to database`() {
        // given
        val artistName = "Michael Jackson"
        val albumName = "Thriller"
        val mbId = "123"

        coEvery {
            mockService.getAlbumInfoAsync(artistName, albumName, null)
        } returns ApiResult.Exception(UnknownHostException())

        // when
        val actual = runBlocking { cut.getAlbumInfo(artistName, albumName, null) }

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
            mockService.getAlbumInfoAsync(artistName, albumName, null)
        } returns mockk<ApiResult.Error<GetAlbumInfoResponse>>()

        // when
        val actual = runBlocking { cut.getAlbumInfo(artistName, albumName, null) }

        // then
        coVerify { mockAlbumDao.getAlbum(artistName, albumName, mbId) }
    }
}
