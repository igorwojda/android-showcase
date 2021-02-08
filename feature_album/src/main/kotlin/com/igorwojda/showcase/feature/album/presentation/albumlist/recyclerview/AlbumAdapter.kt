package com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.igorwojda.showcase.base.delegate.observer
import com.igorwojda.showcase.base.presentation.extension.hide
import com.igorwojda.showcase.base.presentation.extension.setOnDebouncedClickListener
import com.igorwojda.showcase.base.presentation.extension.show
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.databinding.FragmentAlbumListItemBinding
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel

internal class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    var albums: List<AlbumDomainModel> by observer(listOf()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((album: AlbumDomainModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentAlbumListItemBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size

    fun setOnDebouncedClickListener(listener: (album: AlbumDomainModel) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    internal inner class ViewHolder(private val itemBinding: FragmentAlbumListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private var url by observer<String?>(null) {
            itemBinding.coverErrorImageView.hide()

            if (it == null) {
                setDefaultImage()
            } else {
                itemBinding.coverImageView.load(it) {
                    crossfade(true)
                    error(R.drawable.ic_image)
                    transformations(RoundedCornersTransformation(10F))
                }
            }
        }

        fun bind(albumDomainModel: AlbumDomainModel) {
            itemView.setOnDebouncedClickListener { onDebouncedClickListener?.invoke(albumDomainModel) }
            url = albumDomainModel.getDefaultImageUrl()
        }

        private fun setDefaultImage() {
            itemBinding.coverErrorImageView.show()
        }
    }
}
