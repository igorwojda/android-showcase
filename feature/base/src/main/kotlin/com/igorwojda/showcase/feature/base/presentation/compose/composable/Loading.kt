package com.igorwojda.showcase.feature.base.presentation.compose.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.igorwojda.showcase.feature.base.common.res.Dimen
import com.igorwojda.showcase.feature.base.presentation.compose.theme.ShowcaseTheme

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier =
                Modifier
                    .size(Dimen.spaceXXL),
        )
    }
}

@PreviewLightDark
@Composable
private fun LoadingIndicatorPreview() {
    ShowcaseTheme {
        LoadingIndicator()
    }
}
