package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.igorwojda.showcase.feature.base.presentation.compose.theme.ShowcaseTheme

class MainShowcaseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setContent {
            ShowcaseTheme {
                MainShowcaseScreen()
            }
        }
    }
}
