package com.igorwojda.showcase.feature.album.presentation.screen.albumdetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.model.Tag
import com.igorwojda.showcase.feature.album.domain.model.Track
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailViewModel.Action
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Content
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Loading
import com.igorwojda.showcase.feature.base.domain.result.Result.Failure
import com.igorwojda.showcase.feature.base.domain.result.Result.Success
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseState
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

internal class AlbumDetailViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
) : BaseViewModel<UiState, Action>(Loading) {
    fun onInit(albumName: String, artistName: String, albumMbId: String?) {
        getAlbum(albumName, artistName, albumMbId)
    }

    private fun getAlbum(albumName: String, artistName: String, albumMbId: String?) {
        viewModelScope.launch {
            getAlbumUseCase(artistName, albumName, albumMbId).also {
                when (it) {
                    is Success -> {
                        sendAction(Action.AlbumLoadSuccess(it.value))
                    }
                    is Failure -> {
                        sendAction(Action.AlbumLoadFailure)
                    }
                }
            }
        }
    }

    internal sealed interface Action : BaseAction<UiState> {
        class AlbumLoadSuccess(
            private val album: Album,
        ) : Action {
            override fun reduce(state: UiState) =
                Content(
                    artistName = album.artist,
                    albumName = album.name,
                    coverImageUrl = album.getDefaultImageUrl() ?: "",
                    tracks = album.tracks,
                    tags = album.tags,
                )
        }

        object AlbumLoadFailure : Action {
            override fun reduce(state: UiState) = UiState.Error
        }
    }

    @Immutable
    internal sealed interface UiState : BaseState {
        data class Content(
            val albumName: String = "",
            val artistName: String = "",
            val coverImageUrl: String = "",
            val tracks: List<Track>? = emptyList(),
            val tags: List<Tag>? = emptyList(),
        ) : UiState

        data object Loading : UiState

        data object Error : UiState
    }
}
