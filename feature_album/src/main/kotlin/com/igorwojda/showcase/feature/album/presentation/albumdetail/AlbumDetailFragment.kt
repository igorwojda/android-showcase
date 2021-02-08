package com.igorwojda.showcase.feature.album.presentation.albumdetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import coil.load
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.extension.visible
import com.igorwojda.showcase.base.presentation.fragment.InjectionFragment
import kotlinx.android.synthetic.main.fragment_album_detail.*
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : InjectionFragment(R.layout.fragment_album_detail) {

    private val viewModel: AlbumDetailViewModel by instance()

    private val stateObserver = Observer<AlbumDetailViewModel.ViewState> {
        progressBar.visible = it.isLoading

        nameTextView.text = it.albumName
        nameTextView.visible = it.albumName.isNotBlank()

        artistTextView.text = it.artistName
        artistTextView.visible = it.artistName.isNotBlank()

        errorAnimation.visible = it.isError

        val imageSize = 800

        coverImageView.load(it.coverImageUrl) {
            size(imageSize, imageSize)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }
}
