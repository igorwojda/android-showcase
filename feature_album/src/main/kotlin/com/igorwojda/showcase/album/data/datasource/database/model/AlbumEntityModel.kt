package com.igorwojda.showcase.album.data.datasource.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.igorwojda.showcase.album.domain.model.Album
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = "albums")
@TypeConverters(
    AlbumImageEntityTypeConverter::class,
    AlbumTrackEntityTypeConverter::class,
    AlbumTagEntityTypeConverter::class,
)
internal data class AlbumEntityModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mbId: String,
    val name: String,
    val artist: String,
    val images: List<ImageEntityModel> = listOf(),
    val tracks: List<TrackEntityModel>?,
    val tags: List<TagEntityModel>?,
)

internal fun AlbumEntityModel.toDomainModel() =
    Album(
        this.name,
        this.artist,
        this.mbId,
        this.images.mapNotNull { it.toDomainModel() },
        this.tracks?.map { it.toDomainModel() },
        this.tags?.map { it.toDomainModel() },
    )

internal class AlbumImageEntityTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { Json.decodeFromString<List<ImageEntityModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<ImageEntityModel>): String =
        Json.encodeToString(someObjects)
}

internal class AlbumTrackEntityTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { Json.decodeFromString<List<TrackEntityModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<TrackEntityModel>): String =
        Json.encodeToString(someObjects)
}

internal class AlbumTagEntityTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { Json.decodeFromString<List<TagEntityModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<TagEntityModel>): String =
        Json.encodeToString(someObjects)
}
