package com.igorwojda.showcase.feature.base.presentation.compose.composable

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextTitleMedium(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Preview
@Composable
private fun TextTitleMediumPreview() {
    TextTitleMedium(text = "Sample Medium Title")
}
