package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.TagApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.TagRoomModel
import com.igorwojda.showcase.feature.album.domain.model.Tag
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TagMapperTest {
    private val sut = TagMapper()

    @Test
    fun `apiToDomain maps tag correctly`() {
        // given
        val apiModel = TagApiModel("rock")

        // when
        val result = sut.apiToDomain(apiModel)

        // then
        result shouldBeEqualTo Tag("rock")
    }

    @Test
    fun `apiToRoom maps tag correctly`() {
        // given
        val apiModel = TagApiModel("rock")

        // when
        val result = sut.apiToRoom(apiModel)

        // then
        result shouldBeEqualTo TagRoomModel("rock")
    }

    @Test
    fun `roomToDomain maps tag correctly`() {
        // given
        val roomModel = TagRoomModel("rock")

        // when
        val result = sut.roomToDomain(roomModel)

        // then
        result shouldBeEqualTo Tag("rock")
    }
}
