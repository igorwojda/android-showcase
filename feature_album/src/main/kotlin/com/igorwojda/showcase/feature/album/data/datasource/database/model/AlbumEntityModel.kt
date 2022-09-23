package com.igorwojda.showcase.feature.album.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.igorwojda.showcase.feature.album.domain.model.Album
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = "albums")
@TypeConverters(AlbumImageEntityTypeConverter::class)
internal data class AlbumEntityModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mbId: String,
    val name: String,
    val artist: String,
    val images: List<AlbumImageEntityModel> = listOf(),
)

internal fun AlbumEntityModel.toDomainModel() =
    Album(
        this.name,
        this.artist,
        this.images.mapNotNull { it.toDomainModel() },
        null,
        this.mbId
    )

internal class AlbumImageEntityTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { Json.decodeFromString<List<AlbumImageEntityModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<AlbumImageEntityModel>): String =
        Json.encodeToString(someObjects)
}
