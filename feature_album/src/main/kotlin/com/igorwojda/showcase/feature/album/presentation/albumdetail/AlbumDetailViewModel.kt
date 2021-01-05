package com.igorwojda.showcase.feature.album.presentation.albumdetail

import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.Action
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.Action.AlbumLoadFailure
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.Action.AlbumLoadSuccess
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.ViewState
import com.igorwojda.showcase.library.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.library.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.library.base.presentation.viewmodel.BaseViewState
import kotlinx.coroutines.launch

internal class AlbumDetailViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
    private val args: AlbumDetailFragmentArgs
) : BaseViewModel<ViewState, Action>(ViewState()) {

    override fun onLoadData() {
        getAlbum()
    }

    private fun getAlbum() {
        viewModelScope.launch {
            getAlbumUseCase.execute(args.artistName, args.albumName, args.mbId).also {
                when {
                    it is GetAlbumUseCase.Result.Success ->
                        sendAction(AlbumLoadSuccess(it.data))
                    it is GetAlbumUseCase.Result.Error ->
                        sendAction(AlbumLoadFailure)
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is AlbumLoadSuccess -> state.copy(
            isLoading = false,
            isError = false,
            artistName = viewAction.albumDomainModel.artist,
            albumName = viewAction.albumDomainModel.name,
            coverImageUrl = viewAction.albumDomainModel.getDefaultImageUrl() ?: ""
        )
        is AlbumLoadFailure -> state.copy(
            isLoading = false,
            isError = true,
            artistName = "",
            albumName = "",
            coverImageUrl = ""
        )
    }

    internal data
    class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val albumName: String = "",
        val artistName: String = "",
        val coverImageUrl: String = ""
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class AlbumLoadSuccess(val albumDomainModel: AlbumDomainModel) : Action()
        object AlbumLoadFailure : Action()
    }
}
