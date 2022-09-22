package com.igorwojda.showcase.feature.album.presentation.albumlist

import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.base.presentation.nav.NavManager
import com.igorwojda.showcase.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.base.presentation.viewmodel.BaseState
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import kotlinx.coroutines.launch

internal class AlbumListViewModel(
    private val navManager: NavManager,
    private val getAlbumListUseCase: GetAlbumListUseCase,
) : BaseViewModel<AlbumListViewModel.State, AlbumListViewModel.Action>(State()) {

    fun onEnter() {
        getAlbumList()
    }

    override fun onReduceState(action: Action) = when (action) {
        is Action.AlbumListLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            albums = action.albums
        )
        is Action.AlbumListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            albums = listOf()
        )
    }

    private fun getAlbumList() {
        viewModelScope.launch {
            getAlbumListUseCase.execute().also { result ->
                val action = when (result) {
                    is Result.Success -> {
                        if (result.value.isEmpty()) {
                            Action.AlbumListLoadingFailure
                        } else {
                            Action.AlbumListLoadingSuccess(result.value)
                        }
                    }
                    is Result.Failure -> {
                        Action.AlbumListLoadingFailure
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

    internal data class State(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val albums: List<Album> = listOf(),
    ) : BaseState

    internal sealed interface Action : BaseAction {
        class AlbumListLoadingSuccess(val albums: List<Album>) : Action
        object AlbumListLoadingFailure : Action
    }
}
