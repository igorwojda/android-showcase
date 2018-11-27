package com.igorwojda.lastfm.feature.album.presentation

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
import timber.log.Timber

internal class AlbumSearchViewModel(
    private val searchAlbumUseCase: SearchAlbumUseCase
) : BaseViewModel() {
    private val albumSearchMutableLiveData = MutableLiveData<List<AlbumDomainModel>>()
    val albumSearchLiveData = albumSearchMutableLiveData.toLiveData()
    private var searchAlbumJob: Job? = null

    fun searchAlbum(phrase: String) {
        Timber.d("AAA searchAlbum $phrase")
        searchAlbumJob?.cancel()

        searchAlbumJob = runBlocking {
            GlobalScope.launch {
                delay(debounceDelay)
                Timber.d("AAA ${Thread.currentThread()}")
                Timber.d("AAA isActive: ${searchAlbumJob?.isActive} isCOmpleted ${searchAlbumJob?.isCompleted} isCancelled ${searchAlbumJob?.isCancelled}")
                searchAlbumUseCase.execute(phrase).also { albumSearchMutableLiveData.postValue(it) }
                Timber.d("AAA -------")
            }
        }
    }
}

internal class AlbumListViewModelFactory(private val searchAlbumUseCase: SearchAlbumUseCase) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = AlbumSearchViewModel(searchAlbumUseCase) as T
}
