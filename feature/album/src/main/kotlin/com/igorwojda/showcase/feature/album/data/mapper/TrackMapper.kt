package com.igorwojda.showcase.feature.album.data.mapper

import com.igorwojda.showcase.feature.album.data.datasource.api.model.TrackApiModel
import com.igorwojda.showcase.feature.album.data.datasource.database.model.TrackRoomModel
import com.igorwojda.showcase.feature.album.domain.model.Track

internal class TrackMapper {
    fun apiToDomain(apiModel: TrackApiModel): Track =
        Track(
            name = apiModel.name,
            duration = apiModel.duration,
        )

    fun apiToRoom(apiModel: TrackApiModel): TrackRoomModel =
        TrackRoomModel(
            name = apiModel.name,
            duration = apiModel.duration,
        )

    fun roomToDomain(roomModel: TrackRoomModel): Track =
        Track(
            name = roomModel.name,
            duration = roomModel.duration,
        )
}
