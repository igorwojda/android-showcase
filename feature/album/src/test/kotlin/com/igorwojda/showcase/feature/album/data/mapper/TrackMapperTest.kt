package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.TrackApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.TrackRoomModel
import com.igorwojda.showcase.feature.album.domain.model.Track
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class TrackMapperTest {
    private val trackMapper = TrackMapper()

    @Test
    fun `apiToDomain maps track correctly`() {
        // given
        val apiModel = TrackApiModel("Test Track", 180)

        // when
        val result = trackMapper.apiToDomain(apiModel)

        // then
        result shouldBeEqualTo Track("Test Track", 180)
    }

    @Test
    fun `apiToRoom maps track correctly`() {
        // given
        val apiModel = TrackApiModel("Test Track", 180)

        // when
        val result = trackMapper.apiToRoom(apiModel)

        // then
        result shouldBeEqualTo TrackRoomModel("Test Track", 180)
    }

    @Test
    fun `roomToDomain maps track correctly`() {
        // given
        val roomModel = TrackRoomModel("Test Track", 180)

        // when
        val result = trackMapper.roomToDomain(roomModel)

        // then
        result shouldBeEqualTo Track("Test Track", 180)
    }
}
