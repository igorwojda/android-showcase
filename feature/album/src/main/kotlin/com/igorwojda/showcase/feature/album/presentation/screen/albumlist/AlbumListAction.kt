package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseAction

internal sealed interface AlbumListAction : BaseAction<AlbumListUiState> {
    object AlbumListLoadStart : AlbumListAction {
        override fun reduce(state: AlbumListUiState) = AlbumListUiState.Loading
    }

    class AlbumListLoadSuccess(
        private val albums: List<Album>,
    ) : AlbumListAction {
        override fun reduce(state: AlbumListUiState) = AlbumListUiState.Content(albums)
    }

    object AlbumListLoadFailure : AlbumListAction {
        override fun reduce(state: AlbumListUiState) = AlbumListUiState.Error
    }
}
