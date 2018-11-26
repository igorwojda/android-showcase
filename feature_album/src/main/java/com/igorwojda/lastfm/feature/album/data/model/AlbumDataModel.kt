package com.igorwojda.lastfm.feature.album.data.model

import com.igorwojda.lastfm.feature.album.data.enum.AlbumDataImageSize
import com.igorwojda.lastfm.feature.album.data.model.albuminfo.AlbumWikiDataModel
import com.igorwojda.lastfm.feature.album.data.model.albuminfo.toDomainModel
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.squareup.moshi.Json

data class AlbumDataModel(
    val name: String,
    val artist: String,
    val wiki: AlbumWikiDataModel?,
    @field:Json(name = "image") val images: List<AlbumImageDataModel> = listOf()
)

fun AlbumDataModel.toDomainModel() = AlbumDomainModel(
    name = this.name,
    artist = this.artist,
    images = this.images.filter { it.size != AlbumDataImageSize.UNKNOWN }.map { it.toDomainModel() },
    wiki = this.wiki?.toDomainModel()
)
