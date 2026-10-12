package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.showcase.feature.base.domain.result.Result
import com.igorwojda.showcase.feature.base.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class AlbumListViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getAlbumListUseCase: GetAlbumListUseCase,
) : BaseViewModel<AlbumListUiState, AlbumListAction>(AlbumListUiState.Loading) {
    // Query typed by the user. Kept in SavedStateHandle, so it survives configuration change and process death
    val queryFlow: StateFlow<String> = savedStateHandle.getStateFlow(SAVED_QUERY_KEY, "")

    private var job: Job? = null

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun onInit() {
        if (job != null) return

        job =
            viewModelScope.launch {
                queryFlow
                    // Search after user stops typing, but reset results immediately when query is cleared
                    .debounce { query -> if (query.isEmpty()) 0L else SEARCH_DEBOUNCE_MILLIS }
                    .map { query -> query.ifEmpty { DEFAULT_QUERY_NAME } }
                    .distinctUntilChanged()
                    .flatMapLatest { query ->
                        flow {
                            emit(AlbumListAction.AlbumListLoadStart)
                            emit(getAlbumList(query))
                        }
                    }.collect(::sendAction)
            }
    }

    fun onRetry() {
        job?.cancel()
        job = null
        onInit()
    }

    fun onQueryChange(query: String) {
        savedStateHandle[SAVED_QUERY_KEY] = query
    }

    private suspend fun getAlbumList(query: String) =
        when (val result = getAlbumListUseCase(query)) {
            is Result.Success -> AlbumListAction.AlbumListLoadSuccess(result.value)
            is Result.Failure -> AlbumListAction.AlbumListLoadFailure(result.error)
        }

    companion object {
        const val DEFAULT_QUERY_NAME = "Jackson"
        private const val SAVED_QUERY_KEY = "query"
        private const val SEARCH_DEBOUNCE_MILLIS = 300L
    }
}
