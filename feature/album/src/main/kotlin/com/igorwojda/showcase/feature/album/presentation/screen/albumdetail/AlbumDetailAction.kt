package com.igorwojda.showcase.feature.album.presentation.screen.albumdetail

import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseAction

internal sealed interface AlbumDetailAction : BaseAction<AlbumDetailUiState> {
    object AlbumLoadStart : AlbumDetailAction {
        override fun reduce(state: AlbumDetailUiState) = AlbumDetailUiState.Loading
    }

    class AlbumLoadSuccess(
        private val album: Album,
    ) : AlbumDetailAction {
        override fun reduce(state: AlbumDetailUiState) =
            AlbumDetailUiState.Content(
                artistName = album.artist,
                albumName = album.name,
                coverImageUrl = album.getDefaultImageUrl() ?: "",
                tracks = album.tracks,
                tags = album.tags,
            )
    }

    object AlbumLoadFailure : AlbumDetailAction {
        override fun reduce(state: AlbumDetailUiState) = AlbumDetailUiState.Error
    }
}
