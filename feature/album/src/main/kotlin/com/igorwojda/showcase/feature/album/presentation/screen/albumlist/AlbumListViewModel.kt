package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import androidx.compose.runtime.Immutable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.Action
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.AlbumListUiState
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.AlbumListUiState.Content
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.AlbumListUiState.Error
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.AlbumListUiState.Loading
import com.igorwojda.showcase.feature.base.domain.result.Result
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseAction
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseState
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal class AlbumListViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getAlbumListUseCase: GetAlbumListUseCase,
) : BaseViewModel<AlbumListUiState, Action>(Loading) {
    
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

        sendAction(Action.AlbumListLoadStart)

        job =
            viewModelScope.launch {
                getAlbumListUseCase(query).also { result ->
                    val action =
                        when (result) {
                            is Result.Success -> {
                                Action.AlbumListLoadSuccess(result.value)
                            }
                            is Result.Failure -> {
                                Action.AlbumListLoadFailure
                            }
                        }

                    sendAction(action)
                }
            }
    }

    internal sealed interface Action : BaseAction<AlbumListUiState> {
        object AlbumListLoadStart : Action {
            override fun reduce(state: AlbumListUiState) = Loading
        }

        class AlbumListLoadSuccess(
            private val albums: List<Album>,
        ) : Action {
            override fun reduce(state: AlbumListUiState) = Content(albums)
        }

        object AlbumListLoadFailure : Action {
            override fun reduce(state: AlbumListUiState) = Error
        }
    }

    @Immutable
    internal sealed interface AlbumListUiState : BaseState {
        data class Content(
            val albums: List<Album>,
        ) : AlbumListUiState

        data object Loading : AlbumListUiState

        data object Error : AlbumListUiState
    }
}
