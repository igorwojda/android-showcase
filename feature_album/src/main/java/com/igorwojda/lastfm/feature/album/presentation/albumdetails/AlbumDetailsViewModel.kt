package com.igorwojda.lastfm.feature.album.presentation.albumdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.BaseViewModel
import com.igorwojda.lastfm.feature.base.presentation.extension.toLiveData
import kotlinx.coroutines.launch

internal class AlbumDetailsViewModel(
    private val getAlbumUseCase: GetAlbumUseCase
) : BaseViewModel() {

    private val _state = MutableLiveData<AlbumDomainModel>()
    val state = _state.toLiveData()

    fun getAlbum(artistName: String, albumName: String, mbId: String?) {
            viewModelScope.launch {
                getAlbumUseCase.execute(artistName, albumName, mbId).also { _state.postValue(it) }
            }
    }
}
