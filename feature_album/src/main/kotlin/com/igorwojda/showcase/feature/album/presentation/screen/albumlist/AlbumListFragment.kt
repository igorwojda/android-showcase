package com.igorwojda.showcase.feature.album.presentation.screen.albumlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.igorwojda.showcase.base.common.res.Dimen
import com.igorwojda.showcase.base.presentation.activity.BaseActivity
import com.igorwojda.showcase.base.presentation.activity.BaseActivity.Companion.mainActivity
import com.igorwojda.showcase.base.presentation.activity.BaseFragment
import com.igorwojda.showcase.base.presentation.compose.composable.DataNotFoundAnim
import com.igorwojda.showcase.base.presentation.compose.composable.PlaceholderImage
import com.igorwojda.showcase.base.presentation.compose.composable.ProgressIndicator
import com.igorwojda.showcase.base.presentation.ext.hideKeyboard
import com.igorwojda.showcase.base.presentation.ext.initSearchBehaviour
import com.igorwojda.showcase.base.presentation.ext.showKeyboard
import com.igorwojda.showcase.feature.album.R
import com.igorwojda.showcase.feature.album.domain.model.Album
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Content
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Error
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Loading
import org.koin.androidx.navigation.koinNavGraphViewModel

class AlbumListFragment : BaseFragment() {

    companion object {

        const val MINIMUM_PRODUCT_QUERY_SIZE = 2
        const val DELAY_BEFORE_SUBMITTING_QUERY = 500L

        fun configureAppBar(baseActivity: BaseActivity) {
            baseActivity.apply {
                appBarLayout?.apply {
                    this.elevation = 0f
                    this.isVisible = true
                }

                mainAppToolbar?.layoutTransition = null
                appBarLayout?.layoutTransition = null

                configureDefaultAppBar(baseActivity)
            }
        }

        private fun configureDefaultAppBar(baseActivity: BaseActivity) {
            baseActivity.apply {
                this.searchTextInputEditText?.hideKeyboard()
                this.searchLayout?.updateLayoutParams {
                    this.width = ViewGroup.LayoutParams.WRAP_CONTENT
                }
                this.searchTextInputLayout.apply {
                    this?.isVisible = false
                }
                this.mainAppToolbar.apply {
                    this?.post {
                        this.setTitle(R.string.album)
                        this.logo = null
                    }
                    this?.menu?.clear()
                    this?.inflateMenu(com.igorwojda.showcase.base.R.menu.menu_toolbar_main)
                    this?.setOnMenuItemClickListener { _ ->
                        configureSearchAppBar(baseActivity)
                        true
                    }
                    this?.logo = null
                }
            }
        }

        private fun configureSearchAppBar(baseActivity: BaseActivity) {
            baseActivity.apply {
                this.searchLayout?.updateLayoutParams {
                    this.width = ViewGroup.LayoutParams.MATCH_PARENT
                }
                this.searchTextInputLayout.apply {
                    this?.isVisible = true
                }
                this.mainAppToolbar.apply {
                    this?.title = null
                    this?.setNavigationOnClickListener {
                        configureDefaultAppBar(
                            baseActivity
                        )
                    }
                    this?.menu?.clear()
                    this?.logo = null
                }
                this.searchTextInputEditText?.post {
                    searchTextInputEditText?.requestFocus()
                }
                this.searchTextInputEditText?.showKeyboard()
            }
        }
    }

    private val model: AlbumListViewModel by koinNavGraphViewModel(R.id.albumNavGraph)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AlbumListScreen(model)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.onEnter()
        mainActivity.searchTextInputEditText?.initSearchBehaviour(
            viewLifecycleOwner.lifecycleScope,
            MINIMUM_PRODUCT_QUERY_SIZE,
            DELAY_BEFORE_SUBMITTING_QUERY,
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    mainActivity.searchTextInputEditText?.hideKeyboard()
                    configureDefaultAppBar(mainActivity)
                    model.onEnter(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrBlank()) {
                        mainActivity.searchTextInputEditText?.hideKeyboard()
                        configureDefaultAppBar(mainActivity)
                    }
                    return true
                }
            }
        ).also { mainActivity.searchTextInputEditText?.text?.clear() }
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
        items(items = albums, key = { it.id }) { album ->
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
