package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.BaseViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

internal class AlbumDetailsViewModel(
    private val getAlbumUseCase: GetAlbumUseCase
) : BaseViewModel() {
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
