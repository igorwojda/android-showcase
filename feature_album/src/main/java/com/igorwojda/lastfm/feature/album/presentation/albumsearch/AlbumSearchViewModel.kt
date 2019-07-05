package com.igorwojda.lastfm.feature.album.presentation.albumsearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.BaseViewModel
import com.igorwojda.lastfm.feature.base.presentation.extension.toLiveData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class AlbumSearchViewModel(
    private val searchAlbumUseCase: SearchAlbumUseCase
) : BaseViewModel() {

    private val _state = MutableLiveData<List<AlbumDomainModel>>()
    val state = _state.toLiveData()
    private var searchAlbumJob: Job? = null

    fun searchAlbum(phrase: String) {
        searchAlbumJob?.cancel()

        viewModelScope.launch {
            delay(debounceDelay)
            searchAlbumUseCase.execute(phrase).also { _state.postValue(it) }
        }
    }
}
