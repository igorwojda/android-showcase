package com.igorwojda.showcase.app.presentation

import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.igorwojda.showcase.app.BuildConfig
import com.igorwojda.showcase.app.presentation.util.NavigationDestinationLogger
import com.igorwojda.showcase.feature.album.presentation.screen.albumdetail.AlbumDetailScreen
import com.igorwojda.showcase.feature.album.presentation.screen.albumlist.AlbumListScreen
import com.igorwojda.showcase.feature.favourite.presentation.screen.favourite.FavouriteScreen
import com.igorwojda.showcase.feature.settings.presentation.screen.aboutlibraries.AboutLibrariesScreen
import com.igorwojda.showcase.feature.settings.presentation.screen.settings.SettingsScreen

@Composable
fun MainShowcaseScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    if (BuildConfig.DEBUG) {
        NavigationDestinationLoggerEffect(navController)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationRoute.AlbumsGraph,
            modifier =
                Modifier
                    .padding(innerPadding)
                    .consumeWindowInsets(innerPadding),
        ) {
            albumsGraph(navController)
            favouritesGraph()
            settingsGraph(navController)
        }
    }
}

private fun NavGraphBuilder.albumsGraph(navController: NavController) {
    navigation<NavigationRoute.AlbumsGraph>(startDestination = NavigationRoute.AlbumList) {
        composable<NavigationRoute.AlbumList> {
            AlbumListScreen(
                onNavigateToAlbumDetail = { artistName, albumName, albumMbId ->
                    navController.navigate(
                        NavigationRoute.AlbumDetail(
                            albumName = albumName,
                            artistName = artistName,
                            albumMbId = albumMbId,
                        ),
                    )
                },
            )
        }
        composable<NavigationRoute.AlbumDetail> { backStackEntry ->
            // Retrieve typed args
            val args = backStackEntry.toRoute<NavigationRoute.AlbumDetail>()

            AlbumDetailScreen(
                albumName = args.albumName,
                artistName = args.artistName,
                albumMbId = args.albumMbId,
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}

private fun NavGraphBuilder.favouritesGraph() {
    navigation<NavigationRoute.FavouritesGraph>(startDestination = NavigationRoute.Favourites) {
        composable<NavigationRoute.Favourites> {
            FavouriteScreen()
        }
    }
}

private fun NavGraphBuilder.settingsGraph(navController: NavController) {
    navigation<NavigationRoute.SettingsGraph>(startDestination = NavigationRoute.Settings) {
        composable<NavigationRoute.Settings> {
            SettingsScreen(
                onNavigateToAboutLibraries = {
                    navController.navigate(NavigationRoute.AboutLibraries)
                },
            )
        }
        composable<NavigationRoute.AboutLibraries> {
            AboutLibrariesScreen(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}

@Composable
private fun NavigationDestinationLoggerEffect(navController: NavController) {
    DisposableEffect(navController) {
        val listener =
            NavController.OnDestinationChangedListener { _, destination, arguments ->
                NavigationDestinationLogger.logDestinationChange(destination, arguments)
            }
        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }
}
