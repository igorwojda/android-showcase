package com.igorwojda.lastfm.feature.album.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igorwojda.lastfm.feature.album.domain.model.OldAlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.SearchAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.extension.toLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AlbumSearchViewModel(
    private val searchAlbumUseCase: SearchAlbumUseCase
) : ViewModel() {
    private val albumSearchMutableLiveData = MutableLiveData<List<OldAlbumDomainModel>>()
    val albumSearchLiveData = albumSearchMutableLiveData.toLiveData()

    init {
        init()
    }

    fun init() {
        runBlocking {
            launch {
                searchAlbumUseCase.execute("believe").also { albumSearchMutableLiveData.postValue(it) }
            }
        }
    }
}

class AlbumListViewModelFactory(private val searchAlbumUseCase: SearchAlbumUseCase) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = AlbumSearchViewModel(searchAlbumUseCase) as T
}
