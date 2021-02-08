package com.igorwojda.showcase.feature.album.presentation.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import coil.load
import com.igorwojda.showcase.base.presentation.extension.observe
import com.igorwojda.showcase.base.presentation.extension.visible
import com.igorwojda.showcase.base.presentation.fragment.InjectionFragment
import com.igorwojda.showcase.feature.album.databinding.FragmentAlbumDetailBinding
import org.kodein.di.generic.instance

internal class AlbumDetailFragment : InjectionFragment() {

    private val viewModel: AlbumDetailViewModel by instance()

    private var _binding: FragmentAlbumDetailBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private val stateObserver = Observer<AlbumDetailViewModel.ViewState> {
        binding.progressBar.visible = it.isLoading

        binding.nameTextView.text = it.albumName
        binding.nameTextView.visible = it.albumName.isNotBlank()

        binding.artistTextView.text = it.artistName
        binding.artistTextView.visible = it.artistName.isNotBlank()

        binding.errorAnimation.visible = it.isError

        val imageSize = 800

        binding.coverImageView.load(it.coverImageUrl) {
            size(imageSize, imageSize)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.stateLiveData, stateObserver)
        viewModel.loadData()
    }
}
