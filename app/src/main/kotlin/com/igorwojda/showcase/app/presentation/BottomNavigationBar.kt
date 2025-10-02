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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.igorwojda.showcase.app.R

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navigationItems = getBottomNavigationItems()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedNavigationIndex = getSelectedNavigationIndex(currentRoute, navigationItems)

    NavigationBar(
        modifier = modifier,
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex == index,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(0)
                        restoreState = true // Restores previous state if returning
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
            NavigationRoute.AlbumList,
        ),
        NavigationBarItem(
            R.string.bottom_navigation_favorites,
            R.drawable.ic_favorite,
            NavigationRoute.Favourites,
        ),
        NavigationBarItem(
            R.string.bottom_navigation_settings,
            R.drawable.ic_settings,
            NavigationRoute.Settings,
        ),
    )

/*
Returns the index of the selected bottom menu item based on the current route.
If no match is found, it defaults to the first item (index 0).
*/
private fun getSelectedNavigationIndex(
    currentRoute: String?,
    navigationItems: List<NavigationBarItem>,
): Int =
    navigationItems
        .indexOfFirst { item ->
            when (currentRoute) {
                null -> false
                NavigationRoute.AlbumDetail::class.qualifiedName -> item.route is NavigationRoute.AlbumList
                NavigationRoute.AboutLibraries::class.qualifiedName -> item.route is NavigationRoute.Settings
                else -> item.route::class.qualifiedName == currentRoute
            }
        }.takeIf { it >= 0 } ?: 0

data class NavigationBarItem(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: NavigationRoute,
)

@Preview
@Composable
private fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        navController = rememberNavController(),
    )
}
