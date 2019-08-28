package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.data.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.album.data.usecase.ModelFixtures
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import org.amshove.kluent.`should not contain`
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDataModelTest {

    @Test
    fun `Data Model with full data maps to AlbumDomainModel`() {
        // given
        val cut = ModelFixtures.getAlbum()

        // then
        cut.toDomainModel() shouldEqual AlbumDomainModel(
            cut.name, cut.artist, cut.images!!.toDomainModel(), cut.wiki!!.toDomainModel(), cut.mbId!!
        )
    }

    @Test
    fun `Data Model with missing data maps to AlbumDomainModel`() {
        // given
        val cut = ModelFixtures.getMinimalAlbum()

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
        cut.toDomainModel().run {
            size shouldEqual albumDataImageSizes.size - 1
            `should not contain`(AlbumImageDataModel("url", AlbumDataImageSize.UNKNOWN))
        }
    }

    @Test
    fun `list of AlbumImageDataModel mapping filters out empty urls`() {
        // given
        val elementWithEmptyUrl = AlbumImageDataModel("", AlbumDataImageSize.EXTRA_LARGE)
        val cut = listOf(
            elementWithEmptyUrl,
            AlbumImageDataModel("url", AlbumDataImageSize.EXTRA_LARGE)
        )

        // then
        cut.toDomainModel().run {
            size shouldEqual 1
            `should not contain`(elementWithEmptyUrl)
        }
    }
}
