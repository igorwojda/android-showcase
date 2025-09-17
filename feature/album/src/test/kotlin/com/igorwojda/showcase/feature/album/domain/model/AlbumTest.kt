package com.igorwojda.showcase.feature.album.domain.model

import com.igorwojda.showcase.feature.album.domain.DomainFixtures
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumTest {
    private lateinit var sut: Album

    @Test
    fun `get default image url`() {
        // given
        val image = DomainFixtures.getImage()

        // when
        sut = DomainFixtures.getAlbum(images = listOf(image))

        // then
        sut.getDefaultImageUrl() shouldBeEqualTo image.url
    }

    @Test
    fun `get null default image url`() {
        // given
        sut = DomainFixtures.getAlbum(images = listOf())

        // then
        sut.getDefaultImageUrl() shouldBeEqualTo null
    }
}
