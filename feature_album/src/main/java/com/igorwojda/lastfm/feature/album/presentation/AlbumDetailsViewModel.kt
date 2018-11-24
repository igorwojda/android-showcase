package com.igorwojda.lastfm.feature.album.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.extension.toLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AlbumDetailsViewModel(
    private val getAlbumUseCase: GetAlbumUseCase
) : ViewModel() {

    private val albumMutableLiveData: MutableLiveData<AlbumDomainModel> = MutableLiveData()
    val albumLiveData = albumMutableLiveData.toLiveData()

    private val albumId: Int = 1

    init {
        init()
    }

    fun init() {
        runBlocking {
            launch {
                getAlbumUseCase.execute(albumId).also { albumMutableLiveData.postValue(it) }
            }
        }
    }
}

class AlbumDetailsViewModelFactory(private val albumListUseCase: GetAlbumUseCase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = AlbumDetailsViewModel(albumListUseCase) as T
}
