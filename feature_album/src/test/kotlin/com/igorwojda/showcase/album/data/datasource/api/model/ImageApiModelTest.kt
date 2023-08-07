package com.igorwojda.showcase.album.data.datasource.api.model

import com.igorwojda.showcase.album.data.DataFixtures
import com.igorwojda.showcase.album.domain.model.Image
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class ImageApiModelTest {

    @Test
    fun `map to AlbumWikiDomainModel`() {
        // given
        val url = "url"
        val size = ImageSizeApiModel.EXTRA_LARGE
        val cut = DataFixtures.getImageModelApiModel(url, size)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo Image(url, size.toDomainModel())
    }

    @Test
    fun `crash when mapping unknown AlbumWikiDomainModel`() {
        // given
        val url = "url"
        val size = ImageSizeApiModel.UNKNOWN
        val cut = DataFixtures.getImageModelApiModel(url, size)

        // when
        val func = { cut.toDomainModel() }

        // then
        func shouldThrow IllegalArgumentException::class
    }
}
