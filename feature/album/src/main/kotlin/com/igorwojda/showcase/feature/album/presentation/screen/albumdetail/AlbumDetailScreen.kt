package com.igorwojda.showcase.feature.album.presentation.screen.albumdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.Tag
import com.igorwojda.showcase.feature.album.domain.model.Track
import com.igorwojda.showcase.feature.album.presentation.util.TimeUtil
import com.igorwojda.showcase.feature.base.common.res.Dimen
import com.igorwojda.showcase.feature.base.presentation.compose.composable.ErrorAnim
import com.igorwojda.showcase.feature.base.presentation.compose.composable.LoadingIndicator
import com.igorwojda.showcase.feature.base.presentation.compose.composable.PlaceholderImage
import com.igorwojda.showcase.feature.base.presentation.compose.composable.TextTitleLarge
import com.igorwojda.showcase.feature.base.presentation.compose.composable.TextTitleMedium
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailScreen(
    albumName: String,
    artistName: String,
    albumMbId: String?,
    onBackClick: () -> Unit = {},
) {
    val viewModel: AlbumDetailViewModel = koinViewModel()
    // Initialize the viewModel with args when the composable enters composition
    LaunchedEffect(Unit) {
        viewModel.onInit(albumName, artistName, albumMbId)
    }

    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = albumName) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                },
            )
        },
    ) { _ ->
        when (val currentUiState = uiState) {
            AlbumDetailUiState.Error -> ErrorAnim()
            AlbumDetailUiState.Loading -> LoadingIndicator()
            is AlbumDetailUiState.Content ->
                AlbumDetailContent(
                    content = currentUiState,
                )
        }
    }
}

@Composable
private fun AlbumDetailContent(
    content: AlbumDetailUiState.Content,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .padding(horizontal = Dimen.screenContentPadding)
                .verticalScroll(rememberScrollState()),
    ) {
        ElevatedCard(
            modifier =
                Modifier
                    .padding(Dimen.spaceM)
                    .wrapContentSize()
                    .size(320.dp)
                    .align(CenterHorizontally),
        ) {
            PlaceholderImage(
                url = content.coverImageUrl,
                contentDescription = stringResource(id = R.string.album_cover_content_description),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(Dimen.spaceL))
        TextTitleLarge(text = content.albumName)
        TextTitleMedium(text = content.artistName)
        Spacer(modifier = Modifier.height(Dimen.spaceL))

        if (content.tags?.isNotEmpty() == true) {
            Tags(content.tags)
            Spacer(modifier = Modifier.height(Dimen.spaceL))
        }

        if (content.tracks?.isNotEmpty() == true) {
            TextTitleMedium(text = stringResource(id = R.string.tracks))
            Spacer(modifier = Modifier.height(Dimen.spaceS))
            Tracks(content.tracks)
        }
    }
}

@Composable
private fun Tags(tags: List<Tag>?) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(Dimen.spaceS),
        verticalArrangement = Arrangement.spacedBy(Dimen.spaceS),
    ) {
        tags?.forEach { tag ->
            SuggestionChip(
                onClick = { },
                label = { Text(tag.name) },
            )
        }
    }
}

@Composable
internal fun Tracks(tracks: List<Track>?) {
    tracks?.forEach { track ->
        TrackItem(track)
    }
}

@Composable
internal fun TrackItem(track: Track) {
    Row {
        Icon(Icons.Outlined.Star, contentDescription = null)
        Spacer(modifier = Modifier.width(Dimen.spaceS))

        val text =
            buildString {
                append(track.name)
                track.duration?.let { duration ->
                    append(" ${TimeUtil.formatTime(duration)}")
                }
            }

        Text(text = text)
    }
}

@Preview
@Composable
private fun TrackItemPreview() {
    TrackItem(
        track =
            Track(
                name = "Sample Track",
                duration = 180, // 3 minutes in seconds
            ),
    )
}
