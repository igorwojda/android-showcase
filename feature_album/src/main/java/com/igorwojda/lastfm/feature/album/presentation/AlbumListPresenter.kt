package com.igorwojda.lastfm.feature.album.presentation

import com.igorwojda.lastfm.feature.album.domain.usecase.GetAlbumListUseCase
import com.igorwojda.lastfm.feature.base.presentation.BasePresenter

class AlbumListPresenter : BasePresenter<AlbumListView>() {
    private val getAlbumsUseCase: GetAlbumListUseCase = GetAlbumListUseCase()

    override fun onTakeView() {
        super.onTakeView()

        getAlbumsUseCase.execute()
    }
}
