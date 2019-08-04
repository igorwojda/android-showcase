package com.igorwojda.showcase.feature.album.presentation.albumsearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.igorwojda.base.presentation.BaseViewModel
import com.igorwojda.base.presentation.extension.toLiveData
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.SearchAlbumUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal class AlbumSearchViewModel(
    private val searchAlbumUseCase: SearchAlbumUseCase
) : BaseViewModel() {

    private val _state = MutableLiveData<List<AlbumDomainModel>>()
    val state = _state.toLiveData()
    private var searchAlbumJob: Job? = null

    init {
        searchAlbum("pop")
    }

    fun searchAlbum(phrase: String) {
        searchAlbumJob?.cancel()

        viewModelScope.launch {
            delay(debounceDelay)
            searchAlbumUseCase.execute(phrase).also { _state.postValue(it) }
        }
    }
}
