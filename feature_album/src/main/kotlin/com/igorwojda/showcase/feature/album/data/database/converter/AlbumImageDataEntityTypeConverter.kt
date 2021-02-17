package com.igorwojda.showcase.feature.album.data.database.converter

import androidx.room.TypeConverter
import com.igorwojda.showcase.feature.album.data.network.model.AlbumImageEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class AlbumImageDataEntityTypeConverter {

    private val type = Types.newParameterizedType(List::class.java, AlbumImageEntity::class.java)
    private val adapter: JsonAdapter<List<AlbumImageEntity>> = Moshi.Builder().build().adapter(type)

    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { adapter.fromJson(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<AlbumImageEntity>): String =
        adapter.toJson(someObjects)
}
