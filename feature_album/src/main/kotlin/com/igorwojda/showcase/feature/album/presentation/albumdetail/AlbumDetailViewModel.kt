package com.igorwojda.showcase.feature.album.presentation.albumdetail

import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.base.presentation.viewmodel.BaseState
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.Action
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.Action.AlbumLoadFailure
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.Action.AlbumLoadSuccess
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.State
import kotlinx.coroutines.launch

internal class AlbumDetailViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
) : BaseViewModel<State, Action>(State()) {

    fun onEnter(args: AlbumDetailFragmentArgs) {
        getAlbum(args)
    }

    private fun getAlbum(args: AlbumDetailFragmentArgs) {
        viewModelScope.launch {
            getAlbumUseCase.execute(args.artistName, args.albumName, args.mbId).also {
                when (it) {
                    is Result.Success -> sendAction(AlbumLoadSuccess(it.value))
                    is Result.Failure -> sendAction(AlbumLoadFailure)
                }
            }
        }
    }

    internal data class State(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val albumName: String = "",
        val artistName: String = "",
        val coverImageUrl: String = "",
    ) : BaseState

    internal sealed interface Action : BaseAction<State> {
        class AlbumLoadSuccess(private val album: Album) : Action {
            override fun reduce(state: State) = state.copy(
                isLoading = false,
                isError = false,
                artistName = album.artist,
                albumName = album.name,
                coverImageUrl = album.getDefaultImageUrl() ?: ""
            )
        }

        object AlbumLoadFailure : Action {
            override fun reduce(state: State) = state.copy(
                isLoading = false,
                isError = true,
                artistName = "",
                albumName = "",
                coverImageUrl = ""
            )
        }
    }
}
