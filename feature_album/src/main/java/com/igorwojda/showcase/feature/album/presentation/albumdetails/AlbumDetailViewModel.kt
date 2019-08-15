package com.igorwojda.showcase.feature.album.presentation.albumdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.base.presentation.extension.toLiveData
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import kotlinx.coroutines.launch

internal class AlbumDetailViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
    private val args: AlbumDetailFragmentArgs
) : BaseViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState = _viewState.toLiveData()

    init {
        _viewState.value = ViewState()
    }

    private val currentViewState
        get() = viewState.value ?: throw RuntimeException("ViewModel state is empty")

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
                    sendAction(ViewAction.AlbumLoadSuccess(it))
                } else {
                    sendAction(ViewAction.AlbumLoadError)
                }
            }
        }
    }
    
    private fun sendAction(viewAction: ViewAction) {
        val st2 = reducer(viewAction)
        _viewState.value = st2
    }

    private fun reducer(viewAction: ViewAction): ViewState = when (viewAction) {
        is ViewAction.ImageLoadingSuccess -> {
            currentViewState.copy(
                isProgressBarVisible = true,
                isError = false
            )
        }
        is ViewAction.ImageLoadingError -> {
            currentViewState.copy(
                isProgressBarVisible = true,
                isError = false
            )
        }
        is ViewAction.AlbumLoadSuccess -> {
            val imageUrl = viewAction.albumDomainModel.images.first().url

            currentViewState.copy(
                isProgressBarVisible = false,
                isError = false,
                artist = viewAction.albumDomainModel.artist,
                name = viewAction.albumDomainModel.name,
                coverImage = imageUrl
            )
        }
        is ViewAction.AlbumLoadError -> {
            currentViewState.copy(
                isProgressBarVisible = false,
                isError = true,
                artist = "",
                name = "",
                coverImage = ""
            )
        }
    }

    data class ViewState(
        val isProgressBarVisible: Boolean = false,
        val isError: Boolean = false,
        val upd: Boolean = true,
        val name: String = "",
        val artist: String = "",
        val coverImage: String = ""
    )

    sealed class ViewAction {
        object ImageLoadingSuccess : ViewAction()
        object ImageLoadingError : ViewAction()
        class AlbumLoadSuccess(val albumDomainModel: AlbumDomainModel) : ViewAction()
        object AlbumLoadError : ViewAction()
    }
}

//val url = viewState.images.firstOrNull { it.size == AlbumDomainImageSize.LARGE }?.url
//
//if (viewState.images.isNotEmpty() && !url.isNullOrEmpty()) {
//    loadImage(url)
//}


//interface ViewState
//
//interface ViewAction
//
//interface Reducer<S : ViewState, A : ViewAction> {
//    fun reduce(state: S, action: A)
//}


// initial viewState
// State.Empty
// TImetravel debugger
// Coroutines flow is  quite helpfull
//middleeware (secase, reepo?) emmits loading action (SearchLoadingAction, SearchSuccesAction)...hmm better foe few views using the same usecase?
