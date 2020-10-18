package com.igorwojda.showcase.feature.album.data.room.entity

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class AlbumImageDataEntityTypeConverter {

    private val type = Types.newParameterizedType(List::class.java, AlbumImageDataEntity::class.java)
    private val adapter: JsonAdapter<List<AlbumImageDataEntity>> = Moshi.Builder().build().adapter(type)

    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { adapter.fromJson(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<AlbumImageDataEntity>): String =
        adapter.toJson(someObjects)
}
