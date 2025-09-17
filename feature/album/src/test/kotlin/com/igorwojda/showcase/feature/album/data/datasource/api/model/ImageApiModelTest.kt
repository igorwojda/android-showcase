package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.domain.model.Image
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class ImageApiModelTest {
    @Test
    fun `map to AlbumWikiDomainModel`() {
        // given
        val url = "url"
        val size = ImageSizeApiModel.EXTRA_LARGE
        val sut = DataFixtures.getImageModelApiModel(url, size)

        // when
        val domainModel = sut.toDomainModel()

        // then
        domainModel shouldBeEqualTo Image(url, size.toDomainModel())
    }

    @Test
    fun `crash when mapping unknown AlbumWikiDomainModel`() {
        // given
        val url = "url"
        val size = ImageSizeApiModel.UNKNOWN
        val sut = DataFixtures.getImageModelApiModel(url, size)

        // when
        val func = { sut.toDomainModel() }

        // then
        func shouldThrow IllegalArgumentException::class
    }
}
