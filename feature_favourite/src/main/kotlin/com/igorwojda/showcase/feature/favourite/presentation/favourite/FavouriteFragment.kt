package com.igorwojda.showcase.feature.favourite.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.igorwojda.showcase.base.presentation.compose.UnderConstructionAnim
import com.igorwojda.showcase.base.presentation.compose.theme.ShowcaseTheme
import timber.log.Timber

class FavouriteFragment : Fragment() {

    init {
        Timber.d("AAA FavouriteFragment")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ShowcaseTheme {
                    FavouriteScreen()
                }
            }
        }
    }
}

@Preview
@Composable
internal fun FavouriteScreen() {
    UnderConstructionAnim()
}
