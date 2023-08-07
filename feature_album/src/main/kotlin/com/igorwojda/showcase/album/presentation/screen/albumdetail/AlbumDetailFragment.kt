package com.igorwojda.showcase.album.presentation.screen.albumdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.navArgs
import com.google.accompanist.flowlayout.FlowRow
import com.igorwojda.showcase.album.R
import com.igorwojda.showcase.album.domain.model.Tag
import com.igorwojda.showcase.album.domain.model.Track
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Content
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Error
import com.igorwojda.showcase.album.presentation.screen.albumdetail.AlbumDetailViewModel.UiState.Loading
import com.igorwojda.showcase.album.presentation.util.TimeUtil
import com.igorwojda.showcase.base.common.res.Dimen
import com.igorwojda.showcase.base.presentation.activity.BaseFragment
import com.igorwojda.showcase.base.presentation.compose.composable.DataNotFoundAnim
import com.igorwojda.showcase.base.presentation.compose.composable.PlaceholderImage
import com.igorwojda.showcase.base.presentation.compose.composable.ProgressIndicator
import com.igorwojda.showcase.base.presentation.compose.composable.TextTitleLarge
import com.igorwojda.showcase.base.presentation.compose.composable.TextTitleMedium
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.navigation.koinNavGraphViewModel

internal class AlbumDetailFragment : BaseFragment() {
    private val args: AlbumDetailFragmentArgs by navArgs()
    private val model: AlbumDetailViewModel by koinNavGraphViewModel(R.id.albumNavGraph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        model.onEnter(args)

        return ComposeView(requireContext()).apply {
            setContent {
                AlbumDetailScreen(uiStateFlow = model.uiStateFlow)
            }
        }
    }
}

@Composable
private fun AlbumDetailScreen(uiStateFlow: StateFlow<UiState>) {
    val uiState: UiState by uiStateFlow.collectAsStateWithLifecycle()

    uiState.let {
        when (it) {
            Error -> DataNotFoundAnim()
            Loading -> ProgressIndicator()
            is Content -> PhotoDetails(it)
        }
    }
}

@Composable
private fun PhotoDetails(content: Content) {
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
                modifier = Modifier
                    .fillMaxWidth(),
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
        tags?.forEach {
            ElevatedSuggestionChip(
                label = { Text(it.name) },
                onClick = { },
            )
        }
    }
}

@Composable
internal fun Tracks(tracks: List<Track>?) {
    tracks?.forEach {
        Track(it)
    }
}

@Composable
internal fun Track(track: Track) {
    Row {
        Icon(Icons.Outlined.Star, null)
        Spacer(modifier = Modifier.width(Dimen.spaceS))

        var text = track.name

        track.duration?.let {
            text += " ${TimeUtil.formatTime(track.duration)}"
        }

        Text(text = text)
    }
}
