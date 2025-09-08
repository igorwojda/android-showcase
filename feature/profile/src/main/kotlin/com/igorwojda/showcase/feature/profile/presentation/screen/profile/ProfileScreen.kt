package com.igorwojda.showcase.feature.profile.presentation.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.igorwojda.showcase.feature.base.presentation.compose.composable.UnderConstructionAnim

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        UnderConstructionAnim()
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}
