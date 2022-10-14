package com.igorwojda.showcase.base.presentation.compose.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun Header1(text: String) {
    Text(text = text, fontSize = 30.sp)
}

@Composable
fun Header2(text: String) {
    Text(text = text, fontSize = 20.sp)
}
