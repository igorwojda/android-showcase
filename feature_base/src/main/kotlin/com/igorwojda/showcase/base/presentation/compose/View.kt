package com.igorwojda.showcase.base.presentation.compose

import androidx.compose.runtime.Composable
import com.igorwojda.showcase.base.R

@Composable
fun UnderConstructionAnim() {
    LabeledAnimation(R.string.under_construction, R.raw.lottie_building_screen)
}

@Composable
fun DataNotFoundAnim() {
    LabeledAnimation(R.string.data_not_found, R.raw.lottie_error_screen)
}
