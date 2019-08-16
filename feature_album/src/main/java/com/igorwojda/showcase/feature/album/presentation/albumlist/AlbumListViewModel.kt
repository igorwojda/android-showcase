package com.igorwojda.showcase.feature.album.presentation.albumlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.presentation.extension.toLiveData
import com.igorwojda.showcase.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewState
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import kotlinx.coroutines.launch

internal class AlbumListViewModel(
    private val getAlbumListUseCase: GetAlbumListUseCase
) : BaseViewModel<AlbumListViewModel.ViewState, AlbumListViewModel.Action>() {

    override val initialViewState = ViewState()

    override fun onReduceState(viewAction: Action): ViewState {
        TODO("not implemented")
    }

    private val _state = MutableLiveData<List<AlbumDomainModel>>()

    val state = _state.toLiveData()

    init {
        getAlbumList()
    }

    // ToDo: support error
    // ToDo: support 0 images length

    private fun getAlbumList() {
        viewModelScope.launch {
            getAlbumListUseCase.execute().also { _state.postValue(it) }
        }
    }

    data class ViewState(
        val isLoading: Boolean = true,
        val albums: List<AlbumDomainModel> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        object AlbumListLoadingFailure : Action()
        object AlbumListLoadingSuccess : Action()

    }
}
