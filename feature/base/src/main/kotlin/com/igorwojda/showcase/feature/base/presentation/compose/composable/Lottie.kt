package com.igorwojda.showcase.feature.base.presentation.compose.composable

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.igorwojda.showcase.feature.base.common.res.Dimen

@Composable
fun LabeledAnimation(
    @StringRes label: Int,
    @RawRes assetResId: Int,
) {
    Card(
        modifier =
            Modifier
                .wrapContentSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .wrapContentSize()
                    .padding(Dimen.spaceXL),
        ) {
            TextTitleMedium(text = stringResource(label))
            LottieAssetLoader(assetResId)
        }
    }
}

@Composable
fun LottieAssetLoader(
    @RawRes assetResId: Int,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(assetResId))

    LottieAnimation(
        composition,
        modifier = Modifier.requiredSize(Dimen.imageSize),
    )
}

@Preview
@Composable
private fun LabeledAnimationPreview() {
    LabeledAnimation(
        label = android.R.string.ok,
        assetResId = com.igorwojda.showcase.feature.base.R.raw.lottie_building_screen,
    )
}

@Preview
@Composable
private fun LottieAssetLoaderPreview() {
    LottieAssetLoader(
        assetResId = com.igorwojda.showcase.feature.base.R.raw.lottie_building_screen,
    )
}
