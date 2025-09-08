package com.igorwojda.showcase.feature.base.presentation.compose.composable

import androidx.compose.runtime.Composable
import com.igorwojda.showcase.feature.base.R

@Composable
fun DataNotFoundAnim() {
    LabeledAnimation(R.string.data_not_found, R.raw.lottie_error_screen)
}