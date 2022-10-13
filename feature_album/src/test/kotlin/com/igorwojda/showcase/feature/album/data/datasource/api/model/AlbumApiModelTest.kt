package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.domain.enum.ImageSize
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.model.AlbumTag
import com.igorwojda.showcase.feature.album.domain.model.AlbumTrack
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
            tracks = listOf(AlbumTrack("track", 12)),
            tags = listOf(AlbumTag("tag"))
        )
    }

    @Test
    fun `mapping filters out unknown size`() {
        // given
        val albumDataImages = listOf(AlbumImageSizeApiModel.EXTRA_LARGE, AlbumImageSizeApiModel.UNKNOWN)
            .map { DataFixtures.getAlbumImageModel(size = it) }
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
            .map { DataFixtures.getAlbumImageModel(url = it) }

        val cut = DataFixtures.getAlbumApiModel(images = images)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel.images.single { it.url == "url" }
    }
}
