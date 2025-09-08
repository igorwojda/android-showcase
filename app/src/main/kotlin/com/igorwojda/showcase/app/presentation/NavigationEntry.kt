package com.igorwojda.showcase.app.presentation

sealed interface NavigationEntry {
    data object AlbumList: NavigationEntry

    data class AlbumDetail(
        val albumName: String,
        val mbId: String
    ): NavigationEntry

    data object Favourite: NavigationEntry
    data object Profile: NavigationEntry
}
