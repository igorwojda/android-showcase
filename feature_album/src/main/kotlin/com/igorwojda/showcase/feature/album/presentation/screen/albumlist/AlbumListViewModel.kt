package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import androidx.compose.runtime.Immutable
import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.base.presentation.nav.NavManager
import com.igorwojda.showcase.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.base.presentation.viewmodel.BaseState
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.Action
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Content
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Error
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Loading
import kotlinx.coroutines.launch

internal class AlbumListViewModel(
    private val navManager: NavManager,
    private val getAlbumListUseCase: GetAlbumListUseCase,
) : BaseViewModel<UiState, Action>(Loading) {

    fun onEnter() {
        getAlbumList()
    }

    private fun getAlbumList() {
        viewModelScope.launch {
            getAlbumListUseCase().also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.value.isEmpty()) {
                            Action.AlbumListLoadFailure
                        } else {
                            Action.AlbumListLoadSuccess(result.value)
                        }
                    }
                    is Result.Failure -> {
                        Action.AlbumListLoadFailure
                    }
                }
                sendAction(action)
            }
        }
    }

    fun onAlbumClick(album: Album) {
        val navDirections =
            AlbumListFragmentDirections.actionAlbumListToAlbumDetail(album.artist, album.name, album.mbId)

        navManager.navigate(navDirections)
    }

    internal sealed interface Action : BaseAction<UiState> {
        class AlbumListLoadSuccess(private val albums: List<Album>) : Action {
            override fun reduce(state: UiState) = Content(albums)
        }

        object AlbumListLoadFailure : Action {
            override fun reduce(state: UiState) = Error
        }
    }

    @Immutable
    internal sealed interface UiState : BaseState {
        data class Content(val albums: List<Album>) : UiState
        object Loading : UiState
        object Error : UiState
    }
}
