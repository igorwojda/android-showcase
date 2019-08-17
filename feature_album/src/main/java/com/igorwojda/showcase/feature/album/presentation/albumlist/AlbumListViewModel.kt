package com.igorwojda.showcase.feature.album.presentation.albumlist

import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewState
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import kotlinx.coroutines.launch

internal class AlbumListViewModel(
    private val getAlbumListUseCase: GetAlbumListUseCase
) : BaseViewModel<AlbumListViewModel.ViewState, AlbumListViewModel.Action>(ViewState()) {

    init {
        getAlbumList()
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.AlbumListLoadingSuccess -> state.copy(
            isLoading = false,
            albums = viewAction.albums
        )
    }

    private fun getAlbumList() {
        viewModelScope.launch {
            getAlbumListUseCase.execute().also {
                sendAction(Action.AlbumListLoadingSuccess(it))
            }
        }
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val albums: List<AlbumDomainModel> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class AlbumListLoadingSuccess(val albums: List<AlbumDomainModel>) : Action()
    }
}
