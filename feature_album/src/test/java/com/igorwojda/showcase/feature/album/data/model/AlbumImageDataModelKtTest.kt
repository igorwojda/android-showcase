package com.igorwojda.showcase.feature.album.data.model

import com.igorwojda.showcase.feature.album.data.DataFixtures
import com.igorwojda.showcase.feature.album.data.enum.AlbumDataImageSize
import com.igorwojda.showcase.feature.album.data.enum.toDomainEnum
import com.igorwojda.showcase.feature.album.domain.model.AlbumImageDomainModel
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldThrow
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumImageDataModelKtTest {

    @Test
    fun `maps to AlbumWikiDomainModel`() {
        // given
        val url = "url"
        val size = AlbumDataImageSize.EXTRA_LARGE
        val cut = DataFixtures.getAlbumImage(url, size)

        // when
        val domainModel = cut.toDomainModel()

        // then
        domainModel shouldBeEqualTo AlbumImageDomainModel(url, size.toDomainEnum())
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
