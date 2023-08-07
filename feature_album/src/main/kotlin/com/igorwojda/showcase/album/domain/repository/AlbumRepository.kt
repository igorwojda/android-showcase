package com.igorwojda.showcase.album.domain.repository

import com.igorwojda.showcase.base.domain.result.Result
import com.igorwojda.showcase.album.domain.model.Album

internal interface AlbumRepository {

    suspend fun getAlbumInfo(artistName: String, albumName: String, mbId: String?): Result<Album>

    suspend fun searchAlbum(phrase: String?): Result<List<Album>>
}
