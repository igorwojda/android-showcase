package com.igorwojda.showcase.feature.album.presentation.screen.albumdetail

import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.showcase.feature.base.domain.result.Result.Failure
import com.igorwojda.showcase.feature.base.domain.result.Result.Success
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

internal class AlbumDetailViewModel(
    private val getAlbumUseCase: GetAlbumUseCase,
) : BaseViewModel<AlbumDetailUiState, AlbumDetailAction>(AlbumDetailUiState.Loading) {
    fun onInit(albumName: String, artistName: String, albumMbId: String?) {
        getAlbum(albumName, artistName, albumMbId)
    }

    private fun getAlbum(albumName: String, artistName: String, albumMbId: String?) {
        sendAction(AlbumDetailAction.AlbumLoadStart)

        viewModelScope.launch {
            getAlbumUseCase(artistName, albumName, albumMbId).also {
                when (it) {
                    is Success -> {
                        sendAction(AlbumDetailAction.AlbumLoadSuccess(it.value))
                    }
                    is Failure -> {
                        sendAction(AlbumDetailAction.AlbumLoadFailure)
                    }
                }
            }
        }
    }


}
