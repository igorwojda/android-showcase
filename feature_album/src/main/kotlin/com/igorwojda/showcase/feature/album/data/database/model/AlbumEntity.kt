package com.igorwojda.showcase.feature.album.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Entity(tableName = "albums")
@TypeConverters(AlbumImageEntityTypeConverter::class)
data class AlbumEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mbId: String,
    val name: String,
    val artist: String,
    val images: List<AlbumImageEntity> = listOf()
)

internal fun AlbumEntity.toDomainModel() =
    Album(
        this.name, this.artist,
        this.images.mapNotNull { it.toDomainModel() },
        null, this.mbId
    )

class AlbumImageEntityTypeConverter {
    private val type = Types.newParameterizedType(List::class.java, AlbumImageEntity::class.java)
    private val adapter: JsonAdapter<List<AlbumImageEntity>> = Moshi.Builder().build().adapter(type)

    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { adapter.fromJson(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<AlbumImageEntity>): String =
        adapter.toJson(someObjects)
}
