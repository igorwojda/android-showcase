package com.igorwojda.lastfm.feature.album.data.model.searchalbum

import com.igorwojda.lastfm.feature.album.domain.model.searchalbum.AlbumDomainModel
import com.squareup.moshi.Json

data class AlbumDataModel(
    val name: String,
    val artist: String,
    @field:Json(name = "image") val images: List<AlbumImageDataModel> = listOf()
)

fun AlbumDataModel.toDomainModel() = AlbumDomainModel(
    name = this.name,
    artist = this.artist,
    images = this.images.map { it.toDomainModel() }
)
