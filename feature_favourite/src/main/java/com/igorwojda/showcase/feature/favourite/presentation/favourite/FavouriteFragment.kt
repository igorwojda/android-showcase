package com.igorwojda.showcase.feature.favourite.presentation.favourite

import com.igorwojda.showcase.feature.favourite.R
import com.igorwojda.showcase.library.base.presentation.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouriteFragment : BaseContainerFragment() {

    override val layoutResourceId = R.layout.fragment_favourites

    override fun onResume() {
        super.onResume()
        underConstructionAnimation.playAnimation()
    }
}
