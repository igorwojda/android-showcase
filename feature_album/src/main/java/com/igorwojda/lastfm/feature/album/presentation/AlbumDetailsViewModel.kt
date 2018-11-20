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
    private val albumDetailMutableLiveData = MutableLiveData<AlbumDomainModel>()
    val albumDetailLiveData = albumDetailMutableLiveData.toLiveData()

    fun init() {
        runBlocking {
            launch {
                getAlbumUseCase.execute(albumId).also { albumDetailMutableLiveData.postValue(it) }
            }
        }
    }
}

class AlbumDetailsViewModelFactory(private val albumId: Int) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = AlbumDetailsViewModel(
        albumId
    ) as T
}
