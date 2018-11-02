package com.igorwojda.lastfm.feature.album.data.repository

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import com.igorwojda.lastfm.feature.album.data.model.AlbumNetworkModel
import com.igorwojda.lastfm.feature.album.data.model.toDomainModel
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel

class AlbumNetworkRepository : AlbumRepository {
    override suspend fun getAlbumList(): List<AlbumDomainModel> {
        var albums: List<AlbumNetworkModel>? = null

        Fuel.get("/albums").responseObject<List<AlbumNetworkModel>> { _, _, result ->
            result.fold(
                success = {
                    albums = it
                },
                failure = {
                    albums = listOf()
                })
        }

        return albums?.map { it.toDomainModel() } ?: listOf()
    }

    override suspend fun getAlbum(id: Int): AlbumDomainModel? {
        var album: AlbumNetworkModel? = null

        Fuel.get("/albums/$id").responseObject<AlbumNetworkModel> { _, _, result ->
            result.fold(
                success = {
                    album = it
                },
                failure = {
                })
        }

        return album?.toDomainModel()
    }
}
