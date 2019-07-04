package com.igorwojda.lastfm.feature.album.presentation.albumsearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.BaseViewModel
import com.igorwojda.lastfm.feature.base.presentation.extension.toLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

internal class AlbumSearchViewModel(
    private val searchAlbumUseCase: SearchAlbumUseCase
) : BaseViewModel() {
    private val albumSearchMutableLiveData = MutableLiveData<List<AlbumDomainModel>>()
    val albumSearchLiveData = albumSearchMutableLiveData.toLiveData()
    private var searchAlbumJob: Job? = null

    fun searchAlbum(phrase: String) {
        searchAlbumJob?.cancel()

        searchAlbumJob = runBlocking {
            GlobalScope.launch {
                delay(debounceDelay)
                searchAlbumUseCase.execute(phrase).also { albumSearchMutableLiveData.postValue(it) }
            }
        }
    }
}

internal class AlbumListViewModelFactory(private val searchAlbumUseCase: SearchAlbumUseCase) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = AlbumSearchViewModel(
        searchAlbumUseCase
    ) as T
}
