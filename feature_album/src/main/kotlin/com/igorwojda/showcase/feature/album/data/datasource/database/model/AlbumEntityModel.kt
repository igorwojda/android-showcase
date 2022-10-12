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
@TypeConverters(
    AlbumImageEntityTypeConverter::class,
    AlbumTrackEntityTypeConverter::class,
    AlbumTagEntityTypeConverter::class
)
internal data class AlbumEntityModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mbId: String,
    val name: String,
    val artist: String,
    val images: List<AlbumImageEntityModel> = listOf(),
    val tracks: List<AlbumTrackEntityModel>?,
    val tags: List<AlbumTagEntityModel>?,
)

internal fun AlbumEntityModel.toDomainModel() =
    Album(
        this.name,
        this.artist,
        null,
        this.mbId,
        this.images.mapNotNull { it.toDomainModel() },
        this.tracks?.map { it.toDomainModel() },
        this.tags?.map { it.toDomainModel() }
    )

internal class AlbumImageEntityTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { Json.decodeFromString<List<AlbumImageEntityModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<AlbumImageEntityModel>): String =
        Json.encodeToString(someObjects)
}

internal class AlbumTrackEntityTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { Json.decodeFromString<List<AlbumTrackEntityModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<AlbumTrackEntityModel>): String =
        Json.encodeToString(someObjects)
}

internal class AlbumTagEntityTypeConverter {
    @TypeConverter
    fun stringToList(data: String?) =
        data?.let { Json.decodeFromString<List<AlbumTagEntityModel>>(it) } ?: listOf()

    @TypeConverter
    fun listToString(someObjects: List<AlbumTagEntityModel>): String =
        Json.encodeToString(someObjects)
}
