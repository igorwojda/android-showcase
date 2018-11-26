package com.igorwojda.lastfm.feature.album.presentation.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igorwojda.lastfm.feature.album.R
import com.igorwojda.lastfm.feature.album.domain.model.OldAlbumDomainModel
import com.igorwojda.lastfm.feature.album.presentation.recyclerview.AlbumAdapter.MyViewHolder
import kotlinx.android.synthetic.main.item_album.view.*
import kotlin.properties.Delegates

class AlbumAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var albums by Delegates.observable(listOf<OldAlbumDomainModel>()) { _, _, _ ->
        notifyDataSetChanged()
    }

    private var onClickListener: ((album: OldAlbumDomainModel) -> Unit)? = null

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(album: OldAlbumDomainModel) {
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

    fun setOnClickListener(listener: (album: OldAlbumDomainModel) -> Unit) {
        this.onClickListener = listener
    }
}
