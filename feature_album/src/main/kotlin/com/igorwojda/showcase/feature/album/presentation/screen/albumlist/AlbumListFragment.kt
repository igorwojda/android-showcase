package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.igorwojda.showcase.base.common.res.Dimen
import com.igorwojda.showcase.base.presentation.activity.BaseFragment
import com.igorwojda.showcase.base.presentation.compose.composable.DataNotFoundAnim
import com.igorwojda.showcase.base.presentation.compose.composable.PlaceholderImage
import com.igorwojda.showcase.base.presentation.compose.composable.ProgressIndicator
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Content
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Error
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Loading
import org.koin.androidx.navigation.koinNavGraphViewModel

class AlbumListFragment : BaseFragment() {

    private val model: AlbumListViewModel by koinNavGraphViewModel(R.id.albumNavGraph)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        model.onEnter()

        return ComposeView(requireContext()).apply {
            setContent {
                AlbumListScreen(model)
            }
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
private fun AlbumListScreen(viewModel: AlbumListViewModel) {
    val uiState: UiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            Error -> DataNotFoundAnim()
            Loading -> ProgressIndicator()
            is Content -> PhotoGrid(albums = it.albums, viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoGrid(albums: List<Album>, viewModel: AlbumListViewModel) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(Dimen.imageSize),
        contentPadding = PaddingValues(Dimen.screenContentPadding)
    ) {
        items(albums.size) { index ->
            val album = albums[index]

            ElevatedCard(
                modifier = Modifier
                    .padding(Dimen.spaceS)
                    .wrapContentSize(),
                onClick = { viewModel.onAlbumClick(album) }
            ) {
                PlaceholderImage(
                    url = album.getDefaultImageUrl(),
                    contentDescription = stringResource(id = R.string.album_cover_content_description),
                    modifier = Modifier.size(Dimen.imageSize)
                )
            }
        }
    }
}
