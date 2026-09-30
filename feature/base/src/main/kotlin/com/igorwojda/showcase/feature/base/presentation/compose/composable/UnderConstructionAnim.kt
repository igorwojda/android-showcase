package com.igorwojda.showcase.feature.base.presentation.compose.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.igorwojda.showcase.feature.base.R
import com.igorwojda.showcase.feature.base.presentation.compose.theme.ShowcaseTheme

@Composable
fun UnderConstructionAnim() {
    LabeledAnimation(R.string.common_under_construction, R.raw.lottie_building_screen)
}

@PreviewLightDark
@Composable
private fun UnderConstructionAnimPreview() {
    ShowcaseTheme {
        UnderConstructionAnim()
    }
}
