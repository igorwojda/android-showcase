package com.igorwojda.showcase.feature.album.data.enum

import org.junit.Assert
import org.junit.jupiter.api.Test

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

        Assert.fail()
    }
}
