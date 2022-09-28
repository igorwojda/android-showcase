package com.igorwojda.showcase.feature.album.presentation.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.navArgs
import coil.compose.AsyncImage
import com.igorwojda.showcase.base.presentation.compose.DataNotFoundAnim
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.UiState
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.UiState.Content
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.UiState.Error
import com.igorwojda.showcase.feature.album.presentation.albumdetail.AlbumDetailViewModel.UiState.Loading
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class AlbumDetailFragment : Fragment() {
    private val args: AlbumDetailFragmentArgs by navArgs()
    private val model: AlbumDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        model.onEnter(args)

        return ComposeView(requireContext()).apply {
            setContent {
                AlbumDetailScreen(uiStateFlow = model.uiStateFlow)
            }
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun AlbumDetailScreen(uiStateFlow: StateFlow<UiState>) {
    val uiState: UiState by uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            Error -> DataNotFoundAnim()
            Loading -> CircularProgressIndicator()
            is Content -> PhotoDetails(it)
        }
    }
}

@Composable
internal fun PhotoDetails(content: Content) {
    Column {
        Text(text = content.albumName)
        Text(text = content.artistName)
        AsyncImage(
            model = content.coverImageUrl,
            contentDescription = stringResource(id = R.string.album_cover_content_description)
        )
    }
}
