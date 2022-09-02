package com.igorwojda.showcase.feature.album.data.datasource.api.enum

import com.igorwojda.showcase.feature.album.data.datasource.api.model.AlbumImageSizeApi
import com.igorwojda.showcase.feature.album.data.datasource.api.model.toDomainModel
import org.junit.jupiter.api.Test

class AlbumImageSizeApiTest {

    @Test
    fun `maps to AlbumDomainImageSize`() {
        // given
        val dataEnums = AlbumImageSizeApi.values().filterNot { it == AlbumImageSizeApi.UNKNOWN }

        // when
        dataEnums.forEach { it.toDomainModel() }

        // then
        // no explicit check is required, because test will crash if any of
        // the consts in the enums can't be mapped to a domain enum
    }
}
