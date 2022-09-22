package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class AlbumImageApiModelTest {

    @Test
    fun `map to AlbumWikiDomainModel`() {
        // given
        val url = "url"
        val size = AlbumImageSizeApiModel.EXTRA_LARGE
        val cut = DataFixtures.getAlbumImageModel(url, size)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo AlbumImage(url, size.toDomainModel())
    }

    @Test
    fun `crash when mapping unknown AlbumWikiDomainModel`() {
        // given
        val url = "url"
        val size = AlbumImageSizeApiModel.UNKNOWN
        val cut = DataFixtures.getAlbumImageModel(url, size)

        // when
        val func = { cut.toDomainModel() }

        // then
        func shouldThrow IllegalArgumentException::class
    }
}
