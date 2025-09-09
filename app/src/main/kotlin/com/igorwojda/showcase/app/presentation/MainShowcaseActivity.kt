package com.igorwojda.showcase.app.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListScreen
import com.igorwojda.showcase.feature.base.presentation.nav.NavManager
import org.koin.android.ext.android.inject

class MainShowcaseActivity : ComponentActivity(),
    NavController.OnDestinationChangedListener {

    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(colorScheme = getColorScheme()) {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NavigationEntry.AlbumList
                ) {
                    composable< NavigationEntry.AlbumList> {
                        AlbumListScreen()
                    }

                }
            }
        }
    }

    @Composable
    private fun getColorScheme(): ColorScheme {
        val darkTheme: Boolean = isSystemInDarkTheme()
        val dynamicColor: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

        val context = LocalContext.current
        return when {
            dynamicColor -> if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            darkTheme -> darkColorScheme()
            else -> lightColorScheme()
        }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
//        when (destination.label) {
//            DESTINATION_ALBUM_LIST_LABEL -> {
//                AlbumListFragment.configureAppBar(this)
//            }
//            else -> {
//                binding.mainAppbarLayout.visibility = View.GONE
//            }
//        }
    }
}
