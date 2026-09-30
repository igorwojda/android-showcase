package com.igorwojda.showcase.app.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.igorwojda.showcase.app.R
import com.igorwojda.showcase.feature.base.presentation.compose.theme.ShowcaseTheme

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navigationItems = getBottomNavigationItems()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = modifier,
    ) {
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination to avoid building up a large back stack,
                        // saving the state of the tab being left
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when reselecting the same tab
                        launchSingleTop = true
                        // Restore state (scroll position, back stack) when reselecting a previously selected tab
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.iconRes),
                        contentDescription = stringResource(item.titleRes),
                    )
                },
                label = {
                    Text(
                        stringResource(item.titleRes),
                    )
                },
                colors =
                    NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.surface,
                        indicatorColor = MaterialTheme.colorScheme.primary,
                    ),
            )
        }
    }
}

private fun getBottomNavigationItems() =
    listOf(
        NavigationBarItem(
            R.string.bottom_navigation_albums,
            R.drawable.ic_music_library,
            NavigationRoute.AlbumsGraph,
        ),
        NavigationBarItem(
            R.string.bottom_navigation_favorites,
            R.drawable.ic_favorite,
            NavigationRoute.FavouritesGraph,
        ),
        NavigationBarItem(
            R.string.bottom_navigation_settings,
            R.drawable.ic_settings,
            NavigationRoute.SettingsGraph,
        ),
    )

data class NavigationBarItem(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: NavigationRoute,
)

@PreviewLightDark
@Composable
private fun BottomNavigationBarPreview() {
    ShowcaseTheme {
        BottomNavigationBar(
            navController = rememberNavController(),
        )
    }
}
