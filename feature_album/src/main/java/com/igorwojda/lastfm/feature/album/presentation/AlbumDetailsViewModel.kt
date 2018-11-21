package com.igorwojda.lastfm.feature.album.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.extension.toLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AlbumDetailsViewModel(
    private val albumId: Int,
    private val getAlbumUseCase: GetAlbumUseCase = GetAlbumUseCase(),
    private val albumMutableLiveData: MutableLiveData<AlbumDomainModel> = MutableLiveData()
) : ViewModel() {

    val albumLiveData = albumMutableLiveData.toLiveData()

    fun init() {
        runBlocking {
            launch {
                getAlbumUseCase.execute(albumId).also { albumMutableLiveData.postValue(it) }
            }
        }
    }
}
