package com.igorwojda.showcase.feature.album.presentation.albumlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import com.igorwojda.base.presentation.extension.toLiveData
import com.igorwojda.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumListUseCase
import kotlinx.coroutines.launch

internal class AlbumListViewModel(
    private val getAlbumListUseCase: GetAlbumListUseCase
) : BaseViewModel() {

    override fun setArgs(args: NavArgs?) {
    }

    override fun loadData() {
    }

    private val _state = MutableLiveData<List<AlbumDomainModel>>()
    val state = _state.toLiveData()

    init {
        getAlbums()
    }

    private fun getAlbums() {
        viewModelScope.launch {
            getAlbumListUseCase.execute().also { _state.postValue(it) }
        }
    }
}
