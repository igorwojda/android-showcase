package com.igorwojda.showcase.feature.album.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.igorwojda.showcase.feature.album.domain.model.Album
import kotlinx.serialization.json.Json

@Entity(tableName = "albums")
@TypeConverters(
    AlbumImageRoomTypeConverter::class,
    AlbumTrackRoomTypeConverter::class,
    AlbumTagRoomTypeConverter::class,
)
internal data class AlbumRoomModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mbId: String,
    val name: String,
    val artist: String,
    val images: List<ImageRoomModel> = listOf(),
    val tracks: List<TrackRoomModel>?,
    val tags: List<TagRoomModel>?,
)

internal fun AlbumRoomModel.toDomainModel() =
    Album(
        this.name,
        this.artist,
        this.mbId,
        this.images.mapNotNull { it.toDomainModel() },
        this.tracks?.map { it.toDomainModel() },
        this.tags?.map { it.toDomainModel() },
    )

internal class AlbumImageRoomTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) = data?.let { Json.decodeFromString<List<ImageRoomModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<ImageRoomModel>): String = Json.encodeToString(someObjects)
}

internal class AlbumTrackRoomTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) = data?.let { Json.decodeFromString<List<TrackRoomModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<TrackRoomModel>): String = Json.encodeToString(someObjects)
}

internal class AlbumTagRoomTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) = data?.let { Json.decodeFromString<List<TagRoomModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<TagRoomModel>): String = Json.encodeToString(someObjects)
}
