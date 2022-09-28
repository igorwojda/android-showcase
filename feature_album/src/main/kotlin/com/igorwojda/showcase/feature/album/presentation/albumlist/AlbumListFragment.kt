package com.igorwojda.showcase.feature.album.presentation.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.igorwojda.showcase.base.presentation.compose.DataNotFoundAnim
import com.igorwojda.showcase.base.presentation.compose.ProgressIndicator
import com.igorwojda.showcase.base.presentation.compose.theme.ShowcaseTheme
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel.UiState
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel.UiState.Content
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel.UiState.Error
import com.igorwojda.showcase.feature.album.presentation.albumlist.AlbumListViewModel.UiState.Loading
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumListFragment : Fragment() {

    private val model: AlbumListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        model.onEnter()

        return ComposeView(requireContext()).apply {
            setContent {
                ShowcaseTheme {
                    AlbumListScreen(model.uiStateFlow)
                }
            }
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
internal fun AlbumListScreen(uiStateFlow: StateFlow<UiState>) {
    val uiState: UiState by uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            Error -> DataNotFoundAnim()
            Loading -> ProgressIndicator()
            is Content -> PhotoGrid(albums = it.albums)
        }
    }
}

@Composable
internal fun PhotoGrid(albums: List<Album>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(albums.size) { index ->
            Card(
                modifier = Modifier.padding(4.dp)
            ) {
                val album = albums[index]

                AsyncImage(
                    model = album.getDefaultImageUrl(),
                    contentDescription = stringResource(id = R.string.album_image_content_description)
                )
            }
        }
    }
}
