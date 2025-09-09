package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.presentation.composable.SearchBar
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.AlbumListUiState.Content
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.AlbumListUiState.Error
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.AlbumListUiState.Loading
import com.igorwojda.showcase.feature.base.common.res.Dimen
import com.igorwojda.showcase.feature.base.presentation.compose.composable.ErrorAnim
import com.igorwojda.showcase.feature.base.presentation.compose.composable.PlaceholderImage
import com.igorwojda.showcase.feature.base.presentation.compose.composable.ProgressIndicator
import org.koin.androidx.compose.koinViewModel

@Composable
fun AlbumListScreen(
    onNavigateToAlbumDetail: ((artistName: String, albumName: String, albumMbId: String?) -> Unit)? = null
) {
    val viewModel: AlbumListViewModel = koinViewModel()

    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.onInit()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search bar
        SearchBar(
            query = searchQuery,
            onQueryChange = { newQuery ->
                searchQuery = newQuery
            },
            onSearch = { query ->
                if (query.isNotEmpty()) {
                    viewModel.onInit(query)
                } else {
                    viewModel.onInit()
                }
            }
        )

        // Content
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            when (val currentUiState = uiState) {  // Extract to local variable for smart casting
                Error -> ErrorAnim()
                Loading -> ProgressIndicator()
                is Content -> AlbumGrid(
                    albums = currentUiState.albums,  // Use currentState instead of uiState
                    onAlbumClick = { album ->
                        onNavigateToAlbumDetail?.invoke(album.artist, album.name, album.mbId)
                    }
                )
            }
        }
    }
}

@Composable
private fun AlbumGrid(
    albums: List<Album>,
    onAlbumClick: (Album) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(Dimen.imageSize),
        contentPadding = PaddingValues(Dimen.screenContentPadding),
    ) {
        items(items = albums, key = { it.id }) { album ->
            ElevatedCard(
                modifier = Modifier
                    .padding(Dimen.spaceS)
                    .wrapContentSize(),
                onClick = { onAlbumClick(album) },
            ) {
                PlaceholderImage(
                    url = album.getDefaultImageUrl(),
                    contentDescription = stringResource(id = R.string.album_cover_content_description),
                    modifier = Modifier.size(Dimen.imageSize),
                )
            }
        }
    }
}
