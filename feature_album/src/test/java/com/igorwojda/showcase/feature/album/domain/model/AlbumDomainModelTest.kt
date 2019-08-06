package com.igorwojda.showcase.feature.album.domain.model

import com.igorwojda.showcase.feature.album.domain.DomainFixtures
import org.amshove.kluent.shouldEqual
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDomainModelTest {

    private lateinit var cut: AlbumDomainModel

    @Test
    fun `model has default image url`() {
        // given
        val image = DomainFixtures.getAlbumImage()
        cut = DomainFixtures.getAlbum(image)

        // then
        cut.getDefaultImageUrl() shouldEqual image.url
    }

    @Test
    fun `model has null image url`() {
        // given
        cut = DomainFixtures.getAlbum(null)

        // then
        cut.getDefaultImageUrl() shouldEqual null
    }
}
