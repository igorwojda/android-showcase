package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.base.domain.result.Result
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class AlbumListViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getAlbumListUseCase: GetAlbumListUseCase,
) : BaseViewModel<AlbumListUiState, AlbumListAction>(AlbumListUiState.Loading) {
    companion object {
        const val DEFAULT_QUERY_NAME = "Jackson"
        private const val SAVED_QUERY_KEY = "query"
    }

    fun onInit(query: String? = (savedStateHandle[SAVED_QUERY_KEY] as? String) ?: DEFAULT_QUERY_NAME) {
        getAlbumList(query)
    }

    private var job: Job? = null

    private fun getAlbumList(query: String?) {
        if (job != null) {
            job?.cancel()
            job = null
        }

        savedStateHandle[SAVED_QUERY_KEY] = query

        sendAction(AlbumListAction.AlbumListLoadStart)

        job =
            viewModelScope.launch {
                getAlbumListUseCase(query).also { result ->
                    val albumListAction =
                        when (result) {
                            is Result.Success -> {
                                AlbumListAction.AlbumListLoadSuccess(result.value)
                            }
                            is Result.Failure -> {
                                AlbumListAction.AlbumListLoadFailure
                            }
                        }

                    sendAction(albumListAction)
                }
            }
    }
}
