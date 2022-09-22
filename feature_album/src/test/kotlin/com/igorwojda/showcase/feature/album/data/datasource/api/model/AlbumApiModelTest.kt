package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.Album
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumApiModelTest {

    @Test
    fun `data model with full data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.ApiModel.getAlbumApiModel()

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
        val cut = DataFixtures.ApiModel.getAlbumApiModel(
            wiki = null,
            images = emptyList()
        )

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo Album(
            mbId = "mbId",
            name = "album",
            artist = "artist",
            images = emptyList(),
            wiki = null
        )
    }

    @Test
    fun `mapping filters out unknown size`() {
        // given
        val albumDataImages = listOf(AlbumImageSizeApiModel.EXTRA_LARGE, AlbumImageSizeApiModel.UNKNOWN)
            .map { DataFixtures.ApiModel.getAlbumImageModel(size = it) }
        val cut = DataFixtures.ApiModel.getAlbumApiModel(images = albumDataImages)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel.images.single { it.size == AlbumDomainImageSize.EXTRA_LARGE }
    }

    @Test
    fun `mapping filters out blank url`() {
        // given
        val images = listOf("", "url")
            .map { DataFixtures.ApiModel.getAlbumImageModel(url = it) }

        val cut = DataFixtures.ApiModel.getAlbumApiModel(images = images)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel.images.single { it.url == "url" }
    }
}
