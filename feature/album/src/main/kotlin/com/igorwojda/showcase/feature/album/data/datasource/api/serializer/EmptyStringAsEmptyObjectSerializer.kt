package com.igorwojda.showcase.feature.album.data.datasource.api.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

// Last.fm API returns an empty string instead of an object when there is no data (e.g. "tags": "")
internal abstract class EmptyStringAsEmptyObjectSerializer<T : Any>(
    serializer: KSerializer<T>,
) : JsonTransformingSerializer<T>(serializer) {
    override fun transformDeserialize(element: JsonElement): JsonElement =
        if (element == JsonPrimitive("")) JsonObject(emptyMap()) else element
}
