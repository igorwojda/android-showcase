package com.igorwojda.showcase.app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.savedstate.SavedState
import com.igorwojda.showcase.feature.base.presentation.nav.NavManager
import org.koin.android.ext.android.inject

class MainShowcaseActivity : ComponentActivity(),
    NavController.OnDestinationChangedListener {

    private val navManager: NavManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
