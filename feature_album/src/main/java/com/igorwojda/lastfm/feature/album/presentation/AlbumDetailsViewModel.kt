package com.igorwojda.lastfm.feature.album.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.extension.toLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

internal class AlbumDetailsViewModel(
    private val getAlbumUseCase: GetAlbumUseCase
) : ViewModel() {
    private val albumMutableLiveData = MutableLiveData<AlbumDomainModel>()
    val albumLiveData = albumMutableLiveData.toLiveData()

    fun getAlbum(artistName: String, albumName: String, mbId: String?) {
        runBlocking {
            GlobalScope.launch {
                getAlbumUseCase.execute(artistName, albumName, mbId).also { albumMutableLiveData.postValue(it) }
            }
        }
    }
}
