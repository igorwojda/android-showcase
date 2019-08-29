package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.data.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotContain
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDataModelTest {

    @Test
    fun `data model with full data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getAlbum()

        // then
        cut.toDomainModel() shouldEqual AlbumDomainModel(
            cut.name, cut.artist, cut.images!!.toDomainModel(), cut.wiki!!.toDomainModel(), cut.mbId!!
        )
    }

    @Test
    fun `data model with missing data maps to AlbumDomainModel`() {
        // given
        val cut = DataFixtures.getMinimalAlbum()

        // then
        cut.toDomainModel() shouldEqual AlbumDomainModel(
            name = "name", artist = "artist", images = emptyList(), wiki = null, mbId = null
        )
    }

    @Test
    fun `list of AlbumImageDataModel mapping filters out unknown sizes`() {
        // given
        val albumDataImageSizes = AlbumDataImageSize.values()
        val cut = albumDataImageSizes.map { AlbumImageDataModel("url", it) }

        // then
        cut shouldNotContain AlbumImageDataModel("url", AlbumDataImageSize.UNKNOWN)
    }

    @Test
    fun `list of AlbumImageDataModel mapping filters out blank urls`() {
        // given
        val elementWithBlankUrl = AlbumImageDataModel("", AlbumDataImageSize.EXTRA_LARGE)
        val cut = listOf(
            elementWithBlankUrl,
            AlbumImageDataModel("url", AlbumDataImageSize.EXTRA_LARGE)
        )

        // then
        cut shouldNotContain elementWithBlankUrl
    }
}
