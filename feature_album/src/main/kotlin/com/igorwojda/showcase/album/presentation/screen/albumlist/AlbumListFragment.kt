package com.igorwojda.showcase.album.presentation.screen.albumlist

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.igorwojda.showcase.album.R
import com.igorwojda.showcase.album.domain.model.Album
import com.igorwojda.showcase.album.presentation.screen.albumlist.AlbumListViewModel.UiState
import com.igorwojda.showcase.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Content
import com.igorwojda.showcase.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Error
import com.igorwojda.showcase.album.presentation.screen.albumlist.AlbumListViewModel.UiState.Loading
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
import org.koin.androidx.navigation.koinNavGraphViewModel

class AlbumListFragment : BaseFragment() {

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

        mainActivity.searchTextInputEditText?.let { editText ->
            editText.initSearchBehaviour(
                viewLifecycleOwner.lifecycleScope,
                MINIMUM_PRODUCT_QUERY_SIZE,
                DELAY_BEFORE_SUBMITTING_QUERY,
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        editText.hideKeyboard()
                        configureDefaultAppBar(mainActivity)
                        model.onEnter(query)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText.isNullOrBlank()) {
                            editText.hideKeyboard()
                            configureDefaultAppBar(mainActivity)
                        }
                        return true
                    }
                },
            ).also { editText.text?.clear() }
        }
    }

    companion object {
        const val MINIMUM_PRODUCT_QUERY_SIZE = 1

        const val DELAY_BEFORE_SUBMITTING_QUERY = 500L

        fun configureAppBar(baseActivity: BaseActivity) {
            baseActivity.apply {
                appBarLayout?.apply {
                    elevation = 0f
                    isVisible = true
                }

                mainAppToolbar?.layoutTransition = null
                appBarLayout?.layoutTransition = null

                configureDefaultAppBar(baseActivity)
            }
        }

        private fun configureDefaultAppBar(baseActivity: BaseActivity) {
            baseActivity.apply {
                searchTextInputEditText?.hideKeyboard()
                searchLayout?.updateLayoutParams {
                    width = ViewGroup.LayoutParams.WRAP_CONTENT
                }
                searchTextInputLayout?.apply {
                    isVisible = false
                }
                mainAppToolbar?.apply {
                    post {
                        setTitle(R.string.album)
                        logo = null
                    }
                    menu?.clear()
                    inflateMenu(R.menu.menu_toolbar_main)
                    setOnMenuItemClickListener { _ ->
                        configureSearchAppBar(baseActivity)
                        true
                    }
                    logo = null
                }
            }
        }
        private fun configureSearchAppBar(baseActivity: BaseActivity) {
            baseActivity.apply {
                searchLayout?.updateLayoutParams {
                    width = ViewGroup.LayoutParams.MATCH_PARENT
                }

                searchTextInputLayout.apply {
                    this?.isVisible = true
                }

                mainAppToolbar.apply {
                    this?.title = null
                    this?.setNavigationOnClickListener {
                        configureDefaultAppBar(
                            baseActivity,
                        )
                    }
                    this?.menu?.clear()
                    this?.logo = null
                }

                searchTextInputEditText?.let {
                    it.post {
                        it.requestFocus()
                        it.showKeyboard()
                    }
                }
            }
        }
    }
}

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
        contentPadding = PaddingValues(Dimen.screenContentPadding),
    ) {
        items(items = albums, key = { it.id }) { album ->
            ElevatedCard(
                modifier = Modifier
                    .padding(Dimen.spaceS)
                    .wrapContentSize(),
                onClick = { viewModel.onAlbumClick(album) },
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
