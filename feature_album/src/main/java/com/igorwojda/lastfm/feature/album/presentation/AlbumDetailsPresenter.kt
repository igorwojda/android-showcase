package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumUseCase
import com.igorwojda.lastfm.feature.base.presentation.BasePresenter
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumDetailsPresenter : BasePresenter<AlbumDetailsView>() {
    private val albumsUseCase = GetAlbumUseCase()

    fun getAlbum(id: Int) {
//        view?.setAlbum(AlbumDomainModel(1, 1, "AAA"))

        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
            val album = albumsUseCase.execute(id)
            album?.let { view?.setAlbum(it) }
        }
    }
}
