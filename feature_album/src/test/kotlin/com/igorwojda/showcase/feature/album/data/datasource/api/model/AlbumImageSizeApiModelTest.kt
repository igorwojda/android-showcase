package com.igorwojda.showcase.feature.album.data.datasource.api.model

import org.junit.jupiter.api.Test

class AlbumImageSizeApiModelTest {

    @Test
    fun `maps to AlbumDomainImageSize`() {
        // given
        val dataEnums = AlbumImageSizeApiModel.values().filterNot { it == AlbumImageSizeApiModel.UNKNOWN }

        // when
        dataEnums.forEach { it.toDomainModel() }

        // then
        // no explicit check is required, because test will crash if any of
        // the consts in the enums can't be mapped to a domain enum
    }
}
