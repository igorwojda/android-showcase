package com.igorwojda.showcase.feature.album.presentation.albumlist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.showcase.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.AlbumDomainModel
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.AlbumAdapter
import com.igorwojda.showcase.feature.album.presentation.albumlist.recyclerview.GridAutofitLayoutManager
import com.pawegio.kandroid.hide
import kotlinx.android.synthetic.main.fragment_album_list.*
import org.kodein.di.generic.instance

class AlbumListFragment : BaseContainerFragment() {

    private val realViewModel: AlbumListViewModel by instance()

    override val viewModel: BaseViewModel by lazy { realViewModel }

    override val layoutResourceId = R.layout.fragment_album_list

    private val albumAdapter: AlbumAdapter by instance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = checkNotNull(context)

        albumAdapter.setOnDebouncedClickListener {
            val navDirections = AlbumListFragmentDirections.actionAlbumsToAlbumDetail(it.artist, it.name, it.mbId)
            findNavController().navigate(navDirections)
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

        observe(realViewModel.state, ::onStateChange)
    }

    private fun onStateChange(list: List<AlbumDomainModel>) {
        albumAdapter.albums = list
        progressBar.hide()
    }
}
