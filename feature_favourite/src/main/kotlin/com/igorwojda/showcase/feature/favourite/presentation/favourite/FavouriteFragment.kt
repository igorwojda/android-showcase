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

class FavouriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                FavouriteScreen()
            }
        }
    }
}

@Preview
@Composable
private fun FavouriteScreen() {
    UnderConstructionAnim()
}
