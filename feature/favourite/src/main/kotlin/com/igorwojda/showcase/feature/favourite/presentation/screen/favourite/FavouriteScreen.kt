package com.igorwojda.showcase.feature.favourite.presentation.screen.favourite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.igorwojda.showcase.feature.base.presentation.compose.composable.UnderConstructionAnim
import com.igorwojda.showcase.feature.base.presentation.compose.theme.ShowcaseTheme

@Composable
fun FavouriteScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        UnderConstructionAnim()
    }
}

@PreviewLightDark
@Composable
private fun FavouriteScreenPreview() {
    ShowcaseTheme {
        FavouriteScreen()
    }
}
