package com.igorwojda.showcase.feature.album.presentation.albumdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavArgs
import com.igorwojda.base.presentation.extension.toLiveData
import com.igorwojda.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.domain.usecase.GetAlbumUseCase
import kotlinx.coroutines.launch

internal class AlbumDetailsViewModel(
    private val getAlbumUseCase: GetAlbumUseCase
) : BaseViewModel() {

    private lateinit var args: AlbumDetailFragmentArgs

    override fun setArgs(args: NavArgs?) {
        this.args = args as AlbumDetailFragmentArgs
    }

    override fun loadData() {
        getAlbum(args)
    }

    private val _state = MutableLiveData<AlbumDomainModel>()
    val state = _state.toLiveData()

    private fun getAlbum(args: AlbumDetailFragmentArgs) {
        viewModelScope.launch {
            getAlbumUseCase.execute(args.artistName, args.albumName, args.mbId).also { _state.postValue(it) }
        }
    }
}
