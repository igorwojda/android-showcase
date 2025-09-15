package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.igorwojda.showcase.feature.settings.presentation.screen.settings.SettingsScreen
import timber.log.Timber

@Composable
fun MainShowcaseScreen() {
    val navController = rememberNavController()
    addOnDestinationChangedListener(navController)

    Scaffold(
        modifier =
            Modifier
                .fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) },
    ) { innerPadding ->

        val graph =
            navController.createGraph(startDestination = NavigationRoute.AlbumList) {
                composable<NavigationRoute.AlbumList> {
                    AlbumListScreen(
                        // artistName: String, albumName: String, mbId: String?
                        onNavigateToAlbumDetail = { artistName, albumName, albumMbId ->
                            navController.navigate(
                                NavigationRoute.AlbumDetail(artistName, albumName, albumMbId),
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
                composable<NavigationRoute.Favourite> {
                    FavouriteScreen()
                }
                composable<NavigationRoute.Settings> {
                    SettingsScreen()
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

private fun addOnDestinationChangedListener(navController: NavController) {
    navController.addOnDestinationChangedListener(
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?,
            ) {
                logDestinationChange(destination, arguments)
            }

            private fun logDestinationChange(
                destination: NavDestination,
                arguments: Bundle?,
            ) {
                val className = NavigationRoute::class.simpleName
                val destinationRoute = destination.route?.substringAfter("$className.") ?: "Unknown"
                val destinationId = destination.id
                val destinationLabel = destination.label ?: "No Label"

                val logMessage =
                    buildString {
                        appendLine("Navigation destination changed:")
                        appendLine("\tRoute: $destinationRoute")
                        appendLine("\tID: $destinationId")
                        appendLine("\tLabel: $destinationLabel")

                        // Log arguments if they exist
                        arguments?.let { bundle ->
                            if (!bundle.isEmpty) {
                                appendLine("  Arguments:")

                                for (key in bundle.keySet()) {
                                    val value = bundle.get(key)
                                    appendLine("\t\t$key: $value")
                                }
                            }
                        }
                    }

                Timber.tag("Navigation").d(logMessage)
            }
        },
    )
}
