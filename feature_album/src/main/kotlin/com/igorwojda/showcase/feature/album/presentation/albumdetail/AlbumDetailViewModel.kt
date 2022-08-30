package com.igorwojda.showcase.feature.album.presentation.albumdetail

import androidx.lifecycle.viewModelScope
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
                    is GetAlbumUseCase.Result.Success -> sendAction(AlbumLoadSuccess(it.data))
                    is GetAlbumUseCase.Result.Error -> sendAction(AlbumLoadFailure)
                }
            }
        }
    }

    override fun onReduceState(action: Action) = when (action) {
        is AlbumLoadSuccess -> state.copy(
            isLoading = false,
            isError = false,
            artistName = action.album.artist,
            albumName = action.album.name,
            coverImageUrl = action.album.getDefaultImageUrl() ?: ""
        )
        is AlbumLoadFailure -> state.copy(
            isLoading = false,
            isError = true,
            artistName = "",
            albumName = "",
            coverImageUrl = ""
        )
    }

    internal data class State(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val albumName: String = "",
        val artistName: String = "",
        val coverImageUrl: String = "",
    ) : BaseState

    internal sealed interface Action : BaseAction {
        class AlbumLoadSuccess(val album: Album) : Action
        object AlbumLoadFailure : Action
    }
}
