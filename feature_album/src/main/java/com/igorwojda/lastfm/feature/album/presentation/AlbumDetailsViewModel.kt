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
    private val albumId: Int,
    private val getAlbumUseCase: GetAlbumUseCase = GetAlbumUseCase()
) : ViewModel() {
    private val _albumDetailLiveData = MutableLiveData<AlbumDomainModel>()
    val albumDetailLiveData = _albumDetailLiveData.toLiveData()

    fun init() {
        runBlocking {
            launch {
                getAlbumUseCase.execute(albumId).also { _albumDetailLiveData.postValue(it) }
            }
        }
    }
}

class AlbumDetailsViewModelFactory(private val albumId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = AlbumDetailsViewModel(
        albumId
    ) as T
}
