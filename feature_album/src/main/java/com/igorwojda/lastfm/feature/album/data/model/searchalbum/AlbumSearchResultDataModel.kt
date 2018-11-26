package com.igorwojda.lastfm.feature.album.data.model.searchalbum

import com.squareup.moshi.Json

data class AlbumSearchResultDataModel(
    @field:Json(name = "albummatches") val albumMatches: AlbumListDataModel
)
