package com.igorwojda.showcase.feature.base.presentation.compose.composable

import androidx.compose.runtime.Composable
import com.igorwojda.showcase.feature.base.R

@Composable
fun UnderConstructionAnim() {
    LabeledAnimation(R.string.under_construction, R.raw.lottie_building_screen)
}