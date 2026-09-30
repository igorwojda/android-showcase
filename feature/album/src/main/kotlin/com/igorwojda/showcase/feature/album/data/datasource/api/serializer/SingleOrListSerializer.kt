package com.igorwojda.showcase.feature.album.data.datasource.api.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonTransformingSerializer

// Last.fm API returns a single object instead of an array when the list contains only one item
internal abstract class SingleOrListSerializer<T : Any>(
    elementSerializer: KSerializer<T>,
) : JsonTransformingSerializer<List<T>>(ListSerializer(elementSerializer)) {
    override fun transformDeserialize(element: JsonElement): JsonElement = if (element is JsonArray) element else JsonArray(listOf(element))
}
