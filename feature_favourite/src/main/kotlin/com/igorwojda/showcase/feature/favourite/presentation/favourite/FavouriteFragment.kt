package com.igorwojda.showcase.feature.favourite.presentation.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.igorwojda.showcase.base.presentation.compose.ShowcaseTheme
import com.igorwojda.showcase.feature.favourite.R

class FavouriteFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                ShowcaseTheme {
                    FavouriteScreen()
                }
            }
        }
    }
}

@Preview
@Composable
internal fun FavouriteScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {
        Text(
            text = stringResource(R.string.under_construction),
            fontSize = 18.sp
        )
        LottieAssetLoader("lottie_building_screen.json")
    }
}

@Composable
fun LottieAssetLoader(assetName: String) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(assetName))
    LottieAnimation(
        composition,
        modifier = Modifier.requiredSize(150.dp)
    )
}
