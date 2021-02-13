package com.igorwojda.showcase.feature.album.domain.model

import com.igorwojda.showcase.feature.album.domain.DomainFixtures
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumDomainModelTest {

    private lateinit var cut: AlbumDomainModel

    @Test
    fun `model has default image url`() {
        // given
        val image = DomainFixtures.getAlbumImage()

        // when
        cut = DomainFixtures.getAlbum(images = listOf(image))

        // then
        cut.getDefaultImageUrl() shouldBeEqualTo image.url
    }

    @Test
    fun `model has null image url`() {
        // given
        cut = DomainFixtures.getAlbum(images = listOf())

        // then
        cut.getDefaultImageUrl() shouldBeEqualTo null
    }
}
