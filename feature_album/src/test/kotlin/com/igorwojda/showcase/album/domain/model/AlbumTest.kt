package com.igorwojda.showcase.album.domain.model

import com.igorwojda.showcase.album.domain.DomainFixtures
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class AlbumTest {

    private lateinit var cut: Album

    @Test
    fun `get default image url`() {
        // given
        val image = DomainFixtures.getImage()

        // when
        cut = DomainFixtures.getAlbum(images = listOf(image))

        // then
        cut.getDefaultImageUrl() shouldBeEqualTo image.url
    }

    @Test
    fun `get null default image url`() {
        // given
        cut = DomainFixtures.getAlbum(images = listOf())

        // then
        cut.getDefaultImageUrl() shouldBeEqualTo null
    }
}
