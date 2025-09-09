package com.igorwojda.showcase.app.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailScreen
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListScreen
import com.igorwojda.showcase.feature.favourite.presentation.screen.favourite.FavouriteScreen
import com.igorwojda.showcase.feature.profile.presentation.screen.profile.ProfileScreen

class MainShowcaseActivity : ComponentActivity(),
    NavController.OnDestinationChangedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            MainShowcaseScreen()
        }
    }

    @Composable
    fun MainShowcaseScreen() {
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->

            val graph =
                navController.createGraph(startDestination = NavigationRoute.AlbumList) {
                    composable<NavigationRoute.AlbumList> {
                        AlbumListScreen(
                            // artistName: String, albumName: String, mbId: String?
                            onNavigateToAlbumDetail = { artistName, albumName, albumMbId ->
                                navController.navigate(
                                    NavigationRoute.AlbumDetail(artistName, albumName, albumMbId)
                                )
                            }
                        )
                    }
                    composable<NavigationRoute.AlbumDetail> { backStackEntry ->
                        // Retrieve typed args
                        val args = backStackEntry.toRoute<NavigationRoute.AlbumDetail>()

                        AlbumDetailScreen(
                            args.albumName,
                            args.artistName,
                            args.albumMbId
                        )
                    }
                    composable<NavigationRoute.Favourite> {
                        FavouriteScreen()
                    }
                    composable<NavigationRoute.Profile> {
                        ProfileScreen()
                    }
                }
            NavHost(
                navController = navController,
                graph = graph,
                modifier = Modifier.padding(innerPadding)
            )

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


