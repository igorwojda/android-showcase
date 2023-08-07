package com.igorwojda.showcase.album.data.datasource.api.model

import com.igorwojda.showcase.album.data.DataFixtures
import com.igorwojda.showcase.album.domain.enum.ImageSize
import com.igorwojda.showcase.album.domain.model.Album
import com.igorwojda.showcase.album.domain.model.Tag
import com.igorwojda.showcase.album.domain.model.Track
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumApiModelTest {

    @Test
    fun `data model with full data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getAlbumApiModel()

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo Album(
            cut.name,
            cut.artist,
            cut.mbId,
            cut.images?.map { it.toDomainModel() } ?: listOf(),
            cut.tracks?.track?.map { it.toDomainModel() },
            cut.tags?.tag?.map { it.toDomainModel() },
        )
    }

    @Test
    fun `data model with missing data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getAlbumApiModel(
            images = emptyList(),
        )

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo Album(
            mbId = "mbId",
            name = "album",
            artist = "artist",
            images = emptyList(),
            tracks = listOf(Track("track", 12)),
            tags = listOf(Tag("tag")),
        )
    }

    @Test
    fun `mapping filters out unknown size`() {
        // given
        val albumDataImages = listOf(ImageSizeApiModel.EXTRA_LARGE, ImageSizeApiModel.UNKNOWN)
            .map { DataFixtures.getImageModelApiModel(size = it) }
        val cut = DataFixtures.getAlbumApiModel(images = albumDataImages)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel.images.single { it.size == ImageSize.EXTRA_LARGE }
    }

    @Test
    fun `mapping filters out blank url`() {
        // given
        val images = listOf("", "url")
            .map { DataFixtures.getImageModelApiModel(url = it) }

        val cut = DataFixtures.getAlbumApiModel(images = images)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel.images.single { it.url == "url" }
    }
}
