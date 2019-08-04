package com.igorwojda.showcase.feature.album.presentation.albumlist

import android.os.Bundle
import android.view.View
import com.igorwojda.base.presentation.extension.observe
import com.igorwojda.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.presentation.albumdetails.AlbumDetailsActivity
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.AlbumAdapter
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.GridAutofitLayoutManager
import com.pawegio.kandroid.hide
import kotlinx.android.synthetic.main.fragment_album_list.*
import org.kodein.di.generic.instance


class AlbumListFragment : BaseContainerFragment() {

    override val layoutResourceId = R.layout.fragment_album_list

    private val viewModel: AlbumListViewModel by instance()
    private val albumAdapter: AlbumAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = checkNotNull(context)

        albumAdapter.setOnDebouncedClickListener {
            AlbumDetailsActivity.start(context, it.artist, it.name, it.mbId)
        }

        recyclerView.apply {
            setHasFixedSize(true)
            val columnWidth = context.resources.getDimension(R.dimen.image_size).toInt()
            layoutManager =
                GridAutofitLayoutManager(
                    context,
                    columnWidth
                )
            adapter = albumAdapter
        }

        observe(viewModel.state, ::onStateChange)

        loadingSpinner.hide()
    }

    private fun onStateChange(list: List<AlbumDomainModel>) {
        albumAdapter.albums = list
        loadingSpinner.hide()
    }
}
