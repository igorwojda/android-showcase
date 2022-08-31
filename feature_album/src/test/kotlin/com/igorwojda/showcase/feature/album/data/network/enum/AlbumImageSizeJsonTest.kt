package com.igorwojda.showcase.feature.album.data.network.enum

import com.igorwojda.showcase.feature.album.data.network.model.AlbumImageSizeJson
import com.igorwojda.showcase.feature.album.data.network.model.toDomainModel
import org.junit.jupiter.api.Test

class AlbumImageSizeJsonTest {

    @Test
    fun `maps to AlbumDomainImageSize`() {
        // given
        val dataEnums = AlbumImageSizeJson.values().filterNot { it == AlbumImageSizeJson.UNKNOWN }

        // when
        dataEnums.forEach { it.toDomainModel() }

        // then
        // no explicit check is required, because test will crash if any of
        // the consts in the enums can't be mapped to a domain enum
    }
}
