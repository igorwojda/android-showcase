package com.igorwojda.showcase.feature.album.data.enum

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AlbumDataImageSizeTest {

    @Test
    fun `maps to AlbumDomainImageSize`() {
        // given
        val dataEnums = AlbumDataImageSize.values().filterNot { it == AlbumDataImageSize.UNKNOWN }

        // when
        dataEnums.forEach { it.toDomainEnum() }

        // then
        // no explicit check is required, because test will crash if any of
        // the consts in the enums can't be mapped to a domain enum
    }
}
