package com.igorwojda.lastfm.feature.album.data.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import java.io.Reader

data class AlbumNetworkModel(
    val id: Int,
    val userId: Int,
    val title: String?
) {
    class Deserializer : ResponseDeserializable<AlbumDomainModel> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, AlbumDomainModel::class.java)!!
    }

    class ListDeserializer : ResponseDeserializable<List<AlbumDomainModel>> {
        override fun deserialize(reader: Reader): List<AlbumDomainModel> {
            val type = object : TypeToken<List<AlbumDomainModel>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}

fun AlbumNetworkModel.toDomainModel(): AlbumDomainModel {
    return AlbumDomainModel(
        id = this.id,
        userId = this.userId,
        title = this.title ?: ""
    )
}
