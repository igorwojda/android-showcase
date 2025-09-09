package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import androidx.compose.runtime.Immutable
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseState

@Immutable
internal sealed interface AlbumListUiState : BaseState {
    @Immutable
    data class Content(
        val albums: List<Album>,
    ) : AlbumListUiState

    @Immutable
    data object Loading : AlbumListUiState

    @Immutable
    data object Error : AlbumListUiState
}
