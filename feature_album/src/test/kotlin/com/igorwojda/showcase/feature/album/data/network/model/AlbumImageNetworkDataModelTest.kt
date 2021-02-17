package com.igorwojda.showcase.feature.album.data.network.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.data.network.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.album.data.network.enum.toDomainEnum
import com.igorwojda.showcase.feature.album.domain.model.AlbumImage
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.Test

class AlbumImageNetworkDataModelTest {

    @Test
    fun `maps to AlbumWikiDomainModel`() {
        // given
        val url = "url"
        val size = AlbumDataImageSize.EXTRA_LARGE
        val cut = DataFixtures.getAlbumImage(url, size)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo AlbumImage(url, size.toDomainEnum())
    }

    @Test
    fun `crash when mapping unknown AlbumWikiDomainModel`() {
        // given
        val url = "url"
        val size = AlbumDataImageSize.UNKNOWN
        val cut = DataFixtures.getAlbumImage(url, size)

        // when
        val func = { cut.toDomainModel() }

        // then
        func shouldThrow IllegalArgumentException::class
    }
}
