package com.igorwojda.showcase.app.presentation

import kotlinx.serialization.Serializable

sealed interface NavigationEntry {
    @Serializable
    data object AlbumList: NavigationEntry

    @Serializable
    data class AlbumDetail(
        val albumName: String,
        val mbId: String
    ): NavigationEntry

    @Serializable
    data object Favourite: NavigationEntry

    @Serializable
    data object Profile: NavigationEntry
}
