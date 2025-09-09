package com.igorwojda.showcase.feature.base.presentation.compose.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.igorwojda.showcase.feature.base.R

@Composable
fun UnderConstructionAnim() {
    LabeledAnimation(R.string.under_construction, R.raw.lottie_building_screen)
}

@Preview
@Composable
private fun UnderConstructionAnimPreview() {
    UnderConstructionAnim()
}