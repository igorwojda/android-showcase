package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.SavedState
import com.igorwojda.showcase.app.R
import com.igorwojda.showcase.feature.base.presentation.nav.NavManager
import org.koin.android.ext.android.inject

class MainShowcaseActivity : ComponentActivity(),
    NavController.OnDestinationChangedListener {

    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                // Dev-owned back stack; default to AlbumList tab
                val backStack = remember { mutableStateListOf<NavigationEntry>(NavigationEntry.AlbumList) }

                fun goToRoot(root: NavigationEntry) {
                    backStack.clear()
                    backStack.add(root)
                }

                // Back pops detail or exits tab root
                BackHandler(enabled = backStack.size > 1) { backStack.removeLastOrNull() }

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            val currentRoot = getCurrentRoot(backStack)

                            @Composable
                            fun NavigationBarItem(label: String, target: NavigationEntry) {
                                NavigationBarItem(
                                    selected = currentRoot == target,
                                    onClick = { goToRoot(target) },
                                    icon = { /* keep minimal; no icon */ },
                                    label = { Text(label) }
                                )
                            }

                            NavigationBarItem(
                                stringResource(R.string.albums),
                                NavigationEntry.AlbumList
                            )

                            NavigationBarItem(
                                stringResource(R.string.favorites),
                                NavigationEntry.Favourite
                            )

                            NavigationBarItem(
                                stringResource(R.string.profile),
                                NavigationEntry.Profile
                            )
                        }
                    }
                ) { innerPadding ->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryProvider = entryProvider {
                            // --- Tabs ---
                            entry<NavigationEntry.AlbumList> {
                                Column(Modifier.padding(16.dp)) {
                                    Text("AlbumListScreen")
                                    listOf(
                                        "In Rainbows" to "mb-001",
                                        "Kind of Blue" to "mb-002"
                                    ).forEach { (name, id) ->
                                        Text(
                                            "Open \"$name\" ($id)",
                                            modifier = Modifier
                                                .padding(top = 8.dp)
                                                .clickable { backStack.add(NavigationEntry.AlbumDetail(name, id)) }
                                        )
                                    }
                                }
                            }
                            entry<NavigationEntry.Favourite> {
                                Column(Modifier.padding(16.dp)) { Text("FavouriteScreen") }
                            }
                            entry<NavigationEntry.Profile> {
                                Column(Modifier.padding(16.dp)) { Text("ProfileScreen") }
                            }
                            // --- Detail (reachable from AlbumList) ---
                            entry<NavigationEntry.AlbumDetail> { key ->
                                Column(Modifier.padding(16.dp)) {
                                    Text("AlbumDetailScreen")
                                    Text("Album: ${key.albumName}")
                                    Text("MBID: ${key.mbId}")
                                    Text(
                                        "Back", modifier = Modifier
                                            .padding(top = 12.dp)
                                            .clickable { backStack.removeLastOrNull() })
                                }
                            }
                        }
                    )
                }
            }
        }
    }

    private fun getCurrentRoot(backStack: SnapshotStateList<NavigationEntry>): NavigationEntry? {
        val currentRoot = backStack
            // 1. Look at the topmost entry in the back stack (could be null if empty).
            .lastOrNull()
            // 2. Transform it:
            .let {
                when (it) {
                    // 2a. If we’re on a AlbumDetail screen → don’t treat it as a root tab.
                    is NavigationEntry.AlbumDetail -> {
                        NavigationEntry.AlbumList
                    }
                    // 2b. Otherwise → keep the entry (AlbumList, Favourite, Profile).
                    else -> {
                        it
                    }
                }
            }
        return currentRoot
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: SavedState?,
    ) {
        // TODO("not implemented")
    }

//    override fun onDestinationChanged(
//        controller: NavController,
//        destination: NavDestination,
//        arguments: Bundle?,
//    ) {
//        when (destination.label) {
//            DESTINATION_ALBUM_LIST_LABEL -> {
//                AlbumListFragment.configureAppBar(this)
//            }
//            else -> {
//                binding.mainAppbarLayout.visibility = View.GONE
//            }
//        }
//    }
}
