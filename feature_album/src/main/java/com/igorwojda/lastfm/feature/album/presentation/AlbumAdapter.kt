package com.igorwojda.lastfm.feature.album.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.lastfm.feature.album.presentation.AlbumAdapter.MyViewHolder
import kotlinx.android.synthetic.main.item_album.view.*

class AlbumAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var albums = listOf<AlbumDomainModel>()
    private var onClickListener: ((album: AlbumDomainModel) -> Unit)? = null

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(album: AlbumDomainModel) {
            itemView.title.text = album.title
            itemView.setOnClickListener { onClickListener?.invoke(album) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size

    fun setOnClickListener(listener: (album: AlbumDomainModel) -> Unit) {
        this.onClickListener = listener
    }
}
