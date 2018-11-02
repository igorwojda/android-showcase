package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.lastfm.feature.base.presentation.BasePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AlbumListPresenter : BasePresenter<AlbumListView>() {
    private val getAlbumsUseCase: GetAlbumListUseCase = GetAlbumListUseCase()

    override fun onTakeView() {
        super.onTakeView()

        GlobalScope.launch(Dispatchers.IO) {
            view?.setAlbums(getAlbumsUseCase.execute())
        }
    }
}
