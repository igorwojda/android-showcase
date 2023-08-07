package com.igorwojda.showcase.album.data.datasource.api.model

import org.junit.jupiter.api.Test

class ImageSizeApiModelTest {

    @Test
    fun `maps to AlbumDomainImageSize`() {
        // given
        val dataEnums = ImageSizeApiModel.values().filterNot { it == ImageSizeApiModel.UNKNOWN }

        // when
        dataEnums.forEach { it.toDomainModel() }

        // then
        // no explicit check is required, because test will crash if any of
        // the costs in the enums can't be mapped to a domain enum
    }
}
