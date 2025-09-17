package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.domain.enum.ImageSize
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.model.Tag
import com.igorwojda.showcase.feature.album.domain.model.Track
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumApiModelTest {
    @Test
    fun `data model with full data maps to AlbumDomainModel`() {
        // given
        val sut = DataFixtures.getAlbumApiModel()

        // when
        val domainModel = sut.toDomainModel()

        // then
        domainModel shouldBeEqualTo
            Album(
                sut.name,
                sut.artist,
                sut.mbId,
                sut.images?.map { it.toDomainModel() } ?: listOf(),
                sut.tracks?.track?.map { it.toDomainModel() },
                sut.tags?.tag?.map { it.toDomainModel() },
            )
    }

    @Test
    fun `data model with missing data maps to AlbumDomainModel`() {
        // given
        val sut =
            DataFixtures.getAlbumApiModel(
                images = emptyList(),
            )

        // when
        val domainModel = sut.toDomainModel()

        // then
        domainModel shouldBeEqualTo
            Album(
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
        val albumDataImages =
            listOf(ImageSizeApiModel.EXTRA_LARGE, ImageSizeApiModel.UNKNOWN)
                .map { DataFixtures.getImageModelApiModel(size = it) }
        val sut = DataFixtures.getAlbumApiModel(images = albumDataImages)

        // when
        val domainModel = sut.toDomainModel()

        // then
        domainModel.images.single { it.size == ImageSize.EXTRA_LARGE }
    }

    @Test
    fun `mapping filters out blank url`() {
        // given
        val images =
            listOf("", "url")
                .map { DataFixtures.getImageModelApiModel(url = it) }

        val sut = DataFixtures.getAlbumApiModel(images = images)

        // when
        val domainModel = sut.toDomainModel()

        // then
        domainModel.images.single { it.url == "url" }
    }
}
