package com.igorwojda.showcase.feature.album.presentation.albumdetail

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.navArgs
import com.igorwojda.showcase.base.common.res.Dimen
import com.igorwojda.showcase.base.presentation.compose.composable.DataNotFoundAnim
import com.igorwojda.showcase.base.presentation.compose.composable.Header1
import com.igorwojda.showcase.base.presentation.compose.composable.Header2
import com.igorwojda.showcase.base.presentation.compose.composable.PlaceholderImage
import com.igorwojda.showcase.base.presentation.compose.composable.ProgressIndicator
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

@OptIn(ExperimentalLifecycleComposeApi::class)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoDetails(content: Content) {
    Column(
        modifier = Modifier.padding(Dimen.screenContentPadding)
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
                    .fillMaxWidth()
            )
        }

        Header1(text = content.albumName)
        Header2(text = content.artistName)

        Spacer(modifier = Modifier.height(Dimen.spaceL))

        Row {
            content.tags?.forEach {
                ElevatedSuggestionChip(
                    label = { Text(it.name) },
                    onClick = { },
                    modifier = Modifier.padding(Dimen.spaceS)
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimen.spaceL))

        Text(text = content.tracks.toString())
        Spacer(modifier = Modifier.height(Dimen.spaceL))
    }
}
