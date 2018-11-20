package com.igorwojda.lastfm.feature.album.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.lastfm.feature.base.presentation.extension.toLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AlbumListViewModel(
    private val getAlbumListUseCase: GetAlbumListUseCase = GetAlbumListUseCase()
) : ViewModel() {
    private val albumListMutableLiveData = MutableLiveData<List<AlbumDomainModel>>()
    val albumListLiveData = albumListMutableLiveData.toLiveData()

    fun init() {
        runBlocking {
            launch {
                getAlbumListUseCase.execute().also { albumListMutableLiveData.postValue(it) }
            }
        }
    }
}
