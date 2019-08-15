package com.igorwojda.showcase.feature.album.presentation.albumdetails

import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewState
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailViewModel.Action
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailViewModel.Action.*
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailViewModel.ViewState
import kotlinx.coroutines.launch

internal class AlbumDetailViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
    private val args: AlbumDetailFragmentArgs
) : BaseViewModel<ViewState, Action>() {

    override val initialViewState = ViewState()

    override fun onLoadData() {
        getAlbum()
    }

    private fun getAlbum() {
        viewModelScope.launch {
            getAlbumUseCase.execute(
                args.artistName,
                args.albumName,
                args.mbId
            ).also {
                if (it != null) {
                    sendAction(AlbumLoadSuccess(it))
                } else {
                    sendAction(AlbumLoadError)
                }
            }
        }
    }

    override fun onReduce(viewAction: Action) = when (viewAction) {
        is ImageLoadingSuccess -> viewState.copy(
            isProgressBarVisible = true,
            isError = false
        )
        is ImageLoadingError -> viewState.copy(
            isProgressBarVisible = true,
            isError = false
        )
        is AlbumLoadSuccess -> viewState.copy(
            isProgressBarVisible = false,
            isError = false,
            artist = viewAction.albumDomainModel.artist,
            name = viewAction.albumDomainModel.name,
            coverImage = viewAction.albumDomainModel.images.first().url
        )
        is AlbumLoadError -> viewState.copy(
            isProgressBarVisible = false,
            isError = true,
            artist = "",
            name = "",
            coverImage = ""
        )
    }

    data class ViewState(
        val isProgressBarVisible: Boolean = false,
        val isError: Boolean = false,
        val upd: Boolean = true,
        val name: String = "",
        val artist: String = "",
        val coverImage: String = ""
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object ImageLoadingSuccess : Action()
        object ImageLoadingError : Action()
        class AlbumLoadSuccess(val albumDomainModel: AlbumDomainModel) : Action()
        object AlbumLoadError : Action()
    }
}
