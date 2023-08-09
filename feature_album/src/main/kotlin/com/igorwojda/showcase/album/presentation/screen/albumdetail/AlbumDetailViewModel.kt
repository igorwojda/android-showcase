package com.igorwojda.showcase.album.presentation.screen.albumdetail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.album.domain.model.Album
import com.igorwojda.showcase.album.domain.model.Tag
import com.igorwojda.showcase.album.domain.model.Track
import com.igorwojda.showcase.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.Action
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.Action.AlbumLoadFailure
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.Action.AlbumLoadSuccess
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Content
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Loading
import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.base.presentation.viewmodel.BaseState
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

internal class AlbumDetailViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
) : BaseViewModel<UiState, Action>(Loading) {

    fun onEnter(args: AlbumDetailFragmentArgs) {
        getAlbum(args)
    }

    private fun getAlbum(args: AlbumDetailFragmentArgs) {
        viewModelScope.launch {
            getAlbumUseCase(args.artistName, args.albumName, args.mbId).also {
                when (it) {
                    is Result.Success -> {
                        sendAction(AlbumLoadSuccess(it.value))
                    }
                    is Result.Failure -> sendAction(AlbumLoadFailure)
                }
            }
        }
    }

    internal sealed interface Action : BaseAction<UiState> {
        class AlbumLoadSuccess(private val album: Album) : Action {
            override fun reduce(state: UiState) = Content(
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

        object Loading : UiState
        object Error : UiState
    }
}
