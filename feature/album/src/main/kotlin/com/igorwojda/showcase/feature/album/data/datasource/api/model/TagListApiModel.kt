package com.igorwojda.showcase.feature.album.data.datasource.api.model

import com.igorwojda.showcase.feature.album.data.datasource.api.serializer.EmptyStringAsEmptyObjectSerializer
import com.igorwojda.showcase.feature.album.data.datasource.api.serializer.SingleOrListSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TagListApiModel(
    @SerialName("tag")
    @Serializable(with = TagApiModelListSerializer::class)
    val tag: List<TagApiModel> = emptyList(),
)

internal object TagApiModelListSerializer : SingleOrListSerializer<TagApiModel>(TagApiModel.serializer())

internal object TagListApiModelSerializer : EmptyStringAsEmptyObjectSerializer<TagListApiModel>(TagListApiModel.serializer())
