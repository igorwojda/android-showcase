package com.igorwojda.lastfm.feature.album.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.extension.toLiveData
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class AlbumDetailsViewModel(
    private val getAlbumUseCase: GetAlbumUseCase
) : ViewModel() {

    private val albumMutableLiveData: MutableLiveData<AlbumDomainModel> = MutableLiveData()
    private val albumId: Int = 1

    val albumLiveData = albumMutableLiveData.toLiveData()

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
