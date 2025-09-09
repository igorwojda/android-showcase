package com.igorwojda.showcase.app.presentation

import kotlinx.serialization.Serializable

sealed interface NavigationRoute {
    @Serializable
    data object AlbumList : NavigationRoute

    @Serializable
    data class AlbumDetail(
        val albumName: String,
        val artistName: String,
        val albumMbId: String?,
    ) : NavigationRoute

    @Serializable
    data object Favourite : NavigationRoute

    @Serializable
    data object Profile : NavigationRoute
}
