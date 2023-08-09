package com.igorwojda.showcase.base.presentation.compose.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.igorwojda.showcase.base.R
import com.igorwojda.showcase.base.common.res.Dimen

@Composable
fun DataNotFoundAnim() {
    LabeledAnimation(R.string.data_not_found, R.raw.lottie_error_screen)
}

@Composable
fun UnderConstructionAnim() {
    LabeledAnimation(R.string.under_construction, R.raw.lottie_building_screen)
}

@Composable
fun ProgressIndicator() {
    Box {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(Dimen.spaceXXL),
        )
    }
}
