package com.igorwojda.lastfm.feature.album.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.presentation.AlbumAdapter.MyViewHolder
import kotlinx.android.synthetic.main.item_album.view.*
import timber.log.Timber

class AlbumAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var albumList = listOf<AlbumDomainModel>()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setAlbum(album: AlbumDomainModel) {
            itemView.title.text = album.title

            Timber.d("AAA ${album.title}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setAlbum(albumList[position])
    }

    override fun getItemCount(): Int = albumList.size
}
