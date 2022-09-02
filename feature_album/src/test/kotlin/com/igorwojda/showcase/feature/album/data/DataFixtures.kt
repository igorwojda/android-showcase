package com.igorwojda.showcase.feature.album.data

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumApi
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumImageApi
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumImageSizeApi
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumListApi
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumSearchApi
import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumWikiApi
import com.igorwojda.showcase.feature.album.data.datasource.api.response.GetAlbumInfoResponse
import com.igorwojda.showcase.feature.album.data.datasource.api.response.SearchAlbumResponse
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumEntity
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageEntity
import com.igorwojda.showcase.feature.album.data.datasource.database.model.AlbumImageSizeEntity

object DataFixtures {

    internal fun getAlbumApi(
        mbId: String = "mbId",
        name: String = "albumName",
        artist: String = "artistName",
        wiki: AlbumWikiApi? = getAlbumWikiDataModel(),
        images: List<AlbumImageApi>? = listOf(getAlbumImage()),
    ): AlbumApi = AlbumApi(mbId, name, artist, wiki, images)

    internal fun getAlbumsApi() = listOf(
        getAlbumApi(mbId = "mbid1", "albumName1", "artist1"),
        getAlbumApi(mbId = "mbid2", "albumName2", "artist2")
    )

    internal fun getMinimalAlbumApi(): AlbumApi =
        getAlbumApi(
            name = "name",
            artist = "artist",
            mbId = "mbId",
            wiki = null,
            images = null
        )

    internal fun getAlbumImage(
        url: String = "url_${AlbumImageSizeApi.EXTRA_LARGE}",
        size: AlbumImageSizeApi = AlbumImageSizeApi.EXTRA_LARGE,
    ) = AlbumImageApi(url, size)

    internal fun getAlbumWikiDataModel(
        published: String = "published",
        summary: String = "summary",
    ) = AlbumWikiApi(published, summary)

    // TODO unused
    internal fun getAlbumEntity(
        id: Int = 0,
        mbId: String = "mbId",
        name: String = "albumName",
        artist: String = "artistName",
        images: List<AlbumImageEntity> = listOf(getAlbumImageEntity()),
    ): AlbumEntity = AlbumEntity(id, mbId, name, artist, images)

    internal fun getAlbumImageEntity(
        url: String = "url_${AlbumImageSizeApi.EXTRA_LARGE}",
        size: AlbumImageSizeEntity = AlbumImageSizeEntity.EXTRA_LARGE,
    ) = AlbumImageEntity(url, size)

    internal fun getSearchAlbumResponse() = SearchAlbumResponse(
        AlbumSearchApi(
            AlbumListApi(getAlbumsApi())
        )
    )

    internal fun getGetAlbumInfoResponse(
        album: AlbumApi,
    ) = GetAlbumInfoResponse(album)
}
