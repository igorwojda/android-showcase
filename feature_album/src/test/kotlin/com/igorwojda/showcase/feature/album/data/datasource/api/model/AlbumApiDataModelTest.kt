package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.Album
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumApiDataModelTest {

    @Test
    fun `data model with full data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getAlbumApi()

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo Album(
            cut.name,
            cut.artist,
            cut.images?.map { it.toDomainModel() } ?: listOf(),
            cut.wiki?.toDomainModel(),
            cut.mbId
        )
    }

    @Test
    fun `data model with missing data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getMinimalAlbumApi()

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo Album(
            name = "name", artist = "artist", images = emptyList(), wiki = null, mbId = "mbId"
        )
    }

    @Test
    fun `mapping filters out unknown size`() {
        // given
        val albumDataImages = listOf(AlbumImageSizeApi.EXTRA_LARGE, AlbumImageSizeApi.UNKNOWN)
            .map { DataFixtures.getAlbumImage(size = it) }
        val cut = DataFixtures.getAlbumApi(images = albumDataImages)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel.images.single { it.size == AlbumDomainImageSize.EXTRA_LARGE }
    }

    @Test
    fun `mapping filters out blank url`() {
        // given
        val albumDataImages = listOf("", "url")
            .map { DataFixtures.getAlbumImage(url = it) }

        val cut = DataFixtures.getAlbumApi(images = albumDataImages)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel.images.single { it.url == "url" }
    }
}
