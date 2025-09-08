package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Content
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Error
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Loading
import com.igorwojda.showcase.feature.base.common.res.Dimen
import com.igorwojda.showcase.feature.base.presentation.compose.composable.DataNotFoundAnim
import com.igorwojda.showcase.feature.base.presentation.compose.composable.PlaceholderImage
import com.igorwojda.showcase.feature.base.presentation.compose.composable.ProgressIndicator
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

private const val MINIMUM_PRODUCT_QUERY_SIZE = 1
private const val DELAY_BEFORE_SUBMITTING_QUERY = 500L

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumListScreen(
    viewModel: AlbumListViewModel = koinViewModel(),
    onNavigateToAlbumDetail: (String) -> Unit = {}
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.onEnter()
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
                    viewModel.onEnter(query)
                } else {
                    viewModel.onEnter()
                }
            }
        )

        // Content
        Box(modifier = Modifier.fillMaxSize()) {
            when (uiState) {
                Error -> DataNotFoundAnim()
                Loading -> ProgressIndicator()
                is Content -> AlbumGrid(
                    albums = uiState.albums,
                    onAlbumClick = { album ->
                        viewModel.onAlbumClick(album)
                        onNavigateToAlbumDetail(album.id)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var textFieldValue by remember(query) { mutableStateOf(TextFieldValue(query)) }

    // Debounce search - only trigger search after user stops typing
    LaunchedEffect(textFieldValue.text) {
        if (textFieldValue.text.length >= MINIMUM_PRODUCT_QUERY_SIZE) {
            delay(DELAY_BEFORE_SUBMITTING_QUERY)
            onSearch(textFieldValue.text)
            onQueryChange(textFieldValue.text)
        } else if (textFieldValue.text.isEmpty()) {
            // Immediately search when query is cleared
            onSearch("")
            onQueryChange("")
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            textFieldValue = newValue
        },
        placeholder = {
            Text(stringResource(R.string.search_albums))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        trailingIcon = if (textFieldValue.text.isNotEmpty()) {
            {
                IconButton(
                    onClick = {
                        textFieldValue = TextFieldValue("")
                        onSearch("")
                        onQueryChange("")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear search"
                    )
                }
            }
        } else null,
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimen.spaceM)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
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
