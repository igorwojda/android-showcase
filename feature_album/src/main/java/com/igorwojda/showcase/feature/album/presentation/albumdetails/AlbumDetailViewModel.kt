package com.igorwojda.showcase.feature.album.presentation.albumdetails

import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewState
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailViewModel.Action
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailViewModel.Action.AlbumLoadFailure
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailViewModel.Action.AlbumLoadSuccess
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailViewModel.ViewState
import kotlinx.coroutines.launch

internal class AlbumDetailViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
    private val args: AlbumDetailFragmentArgs
) : BaseViewModel<ViewState, Action>() {

    override val initialViewState = ViewState()

    init {
        loadData()
    }

    override fun onLoadData() {
        getAlbum()
    }

    private fun getAlbum() {
        viewModelScope.launch {
            getAlbumUseCase.execute(args.artistName, args.albumName, args.mbId).also {
                if (it != null) {
                    sendAction(AlbumLoadSuccess(it))
                } else {
                    sendAction(AlbumLoadFailure)
                }
            }
        }
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is AlbumLoadSuccess -> viewState.copy(
            isProgressBarVisible = false,
            isError = false,
            artist = viewAction.albumDomainModel.artist,
            name = viewAction.albumDomainModel.name,
            coverImage = viewAction.albumDomainModel.images.first().url
        )
        is AlbumLoadFailure -> viewState.copy(
            isProgressBarVisible = false,
            isError = true,
            artist = "",
            name = "",
            coverImage = ""
        )
    }

    data class ViewState(
        val isProgressBarVisible: Boolean = true,
        val isError: Boolean = false,
        val name: String = "",
        val artist: String = "",
        val coverImage: String = ""
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class AlbumLoadSuccess(val albumDomainModel: AlbumDomainModel) : Action()
        object AlbumLoadFailure : Action()
    }
}
