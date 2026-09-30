package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.api.serializer.EmptyStringAsEmptyObjectSerializer
import com.igorwojda.showcase.feature.album.data.datasource.api.serializer.SingleOrListSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TrackListApiModel(
    @SerialName("track")
    @Serializable(with = TrackApiModelListSerializer::class)
    val track: List<TrackApiModel> = emptyList(),
)

internal object TrackApiModelListSerializer : SingleOrListSerializer<TrackApiModel>(TrackApiModel.serializer())

internal object TrackListApiModelSerializer :
    EmptyStringAsEmptyObjectSerializer<TrackListApiModel>(TrackListApiModel.serializer())
