package com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igorwojda.base.common.delegate.observer
import com.igorwojda.base.presentation.animation.AlphaAnimationHelperFactory
import com.igorwojda.base.presentation.extension.setOnDebouncedClickListener
import com.igorwojda.base.presentation.picasso.PicassoCallback
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.enum.AlbumDomainImageSize
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.pawegio.kandroid.hide
import com.pawegio.kandroid.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_album_list_item.view.*


internal class AlbumAdapter(
    private val picasso: Picasso,
    private val alphaAnimationHelperFactory: AlphaAnimationHelperFactory
) : RecyclerView.Adapter<AlbumAdapter.MyViewHolder>() {

    var albums by observer(listOf<AlbumDomainModel>()) {
        notifyDataSetChanged()
    }

    private var onDebouncedClickListener: ((album: AlbumDomainModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_album_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size

    fun setOnDebouncedClickListener(listener: (album: AlbumDomainModel) -> Unit) {
        this.onDebouncedClickListener = listener
    }

    internal inner class MyViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val alphaAnimationHelper = alphaAnimationHelperFactory.create(itemView, false)

        private var url by observer<String?>(null) {
            itemView.coverErrorImageView.hide()

            if (it == null) {
                setDefaultImage()
            } else {
                loadImage(it)
            }
        }

        fun bind(albumDomainModel: AlbumDomainModel) {
            itemView.setOnDebouncedClickListener { onDebouncedClickListener?.invoke(albumDomainModel) }
            url = albumDomainModel.images.firstOrNull { it.size == AlbumDomainImageSize.EXTRA_LARGE }?.url
        }

        private fun setDefaultImage() {
            alphaAnimationHelper.show()
            itemView.coverErrorImageView.show()
        }

        private fun loadImage(it: String) {

            val callback = PicassoCallback().apply {

                onSuccess { alphaAnimationHelper.show() }

                onError { setDefaultImage() }
            }

            picasso
                .load(it)
                .into(itemView.coverImageView, callback)
        }
    }
}


