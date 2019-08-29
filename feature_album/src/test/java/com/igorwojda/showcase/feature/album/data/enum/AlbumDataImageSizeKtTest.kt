package com.igorwojda.showcase.feature.album.data.enum

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumDataImageSizeKtTest {

    @Test
    fun `maps to AlbumDomainImageSize`() {
        // given
        val dataEnums = enumValues<AlbumDataImageSize>().filterNot { it == AlbumDataImageSize.UNKNOWN }

        // when
        dataEnums.forEach { it.toDomainEnum() }

        // then
        // no explicit check is required, because test will crash if any of
        // the consts in the enums can't be mapped to a domain enum
    }
}
