package com.igorwojda.showcase.feature.album.presentation.screen.albumdetail

import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.flowlayout.FlowRow
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.Tag
import com.igorwojda.showcase.feature.album.domain.model.Track
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Content
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Error
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Loading
import com.igorwojda.showcase.feature.album.presentation.util.TimeUtil
import com.igorwojda.showcase.feature.base.common.res.Dimen
import com.igorwojda.showcase.feature.base.presentation.compose.composable.DataNotFoundAnim
import com.igorwojda.showcase.feature.base.presentation.compose.composable.PlaceholderImage
import com.igorwojda.showcase.feature.base.presentation.compose.composable.ProgressIndicator
import com.igorwojda.showcase.feature.base.presentation.compose.composable.TextTitleLarge
import com.igorwojda.showcase.feature.base.presentation.compose.composable.TextTitleMedium
import org.koin.androidx.compose.koinViewModel

@Composable
fun AlbumDetailScreen(
    args: AlbumDetailFragmentArgs, // Pass the args as parameter
    viewModel: AlbumDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    // Initialize the viewModel with args when the composable enters composition
    LaunchedEffect(args) {
        viewModel.onEnter(args)
    }

    when (uiState) {
        Error -> DataNotFoundAnim()
        Loading -> ProgressIndicator()
        is Content -> AlbumDetails(uiState)
    }
}

@Composable
private fun AlbumDetails(content: Content) {
    Column(
        modifier = Modifier
            .padding(Dimen.screenContentPadding)
            .verticalScroll(rememberScrollState()),
    ) {
        ElevatedCard(
            modifier = Modifier
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
@OptIn(ExperimentalMaterial3Api::class)
private fun Tags(tags: List<Tag>?) {
    FlowRow(mainAxisSpacing = Dimen.spaceM) {
        tags?.forEach { tag ->
            ElevatedSuggestionChip(
                label = { Text(tag.name) },
                onClick = { },
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

        val text = buildString {
            append(track.name)
            track.duration?.let { duration ->
                append(" ${TimeUtil.formatTime(duration)}")
            }
        }

        Text(text = text)
    }
}
