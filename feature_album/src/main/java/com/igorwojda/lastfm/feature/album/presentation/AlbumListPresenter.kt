package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.lastfm.feature.base.presentation.BasePresenter
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumListPresenter : BasePresenter<AlbumListView>() {
    private val getAlbumsUseCase = GetAlbumListUseCase()

    override fun onTakeView() {
        super.onTakeView()

        GlobalScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
            val result = getAlbumsUseCase.execute()
            view?.setAlbums(result)
        }
//        view?.setAlbums(listOf(AlbumDomainModel(1, 1, "A"), AlbumDomainModel(2, 2, "B")))
    }

//    suspend fun loadAlbums() {
//        val albums = GlobalScope.async { getAlbumsUseCase.execute() }
//        view?.setAlbums(albums.await())
//    }
}
