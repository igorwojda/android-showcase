package com.igorwojda.lastfm.feature.album.data.model.searchalbum

import com.squareup.moshi.Json

internal data class AlbumSearchResultDataModel(
    @field:Json(name = "albummatches") val albumMatches: AlbumListDataModel
)
