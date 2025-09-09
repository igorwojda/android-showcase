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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.igorwojda.showcase.app.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = getBottomNavigationItems()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedNavigationIndex =
        navigationItems
            .indexOfFirst {
                it.route::class.qualifiedName == currentRoute
            }.takeIf { it >= 0 } ?: 0

    NavigationBar {
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
                        color =
                            when (index) {
                                selectedNavigationIndex -> MaterialTheme.colorScheme.onSurface
                                else -> MaterialTheme.colorScheme.surface
                            },
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
            R.string.albums,
            R.drawable.ic_round_album_list,
            NavigationRoute.AlbumList,
        ),
        NavigationBarItem(
            R.string.favorites,
            R.drawable.ic_round_favorite,
            NavigationRoute.Favourite,
        ),
        NavigationBarItem(
            R.string.profile,
            R.drawable.ic_round_profile,
            NavigationRoute.Profile,
        ),
    )

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
