package com.igorwojda.showcase.feature.favourite.presentation.favourite

import android.view.View
import com.igorwojda.showcase.feature.favourite.R
import com.igorwojda.showcase.feature.favourite.databinding.FragmentFavouritesBinding
import com.igorwojda.showcase.library.base.presentation.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouriteFragment : BaseContainerFragment<FragmentFavouritesBinding>() {

    override val layoutResourceId = R.layout.fragment_favourites

    override fun onResume() {
        super.onResume()
        underConstructionAnimation.playAnimation()
    }

    override fun setupBinding(view: View) {
        // TODO Create FavouriteViewModel
    }
}
