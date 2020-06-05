package com.igorwojda.showcase.feature.favourite.presentation.favourite

import com.igorwojda.showcase.feature.favourite.R
import com.igorwojda.showcase.library.base.presentation.fragment.InjectionFragment
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouriteFragment : InjectionFragment(R.layout.fragment_favourites) {

    override fun onResume() {
        super.onResume()
        underConstructionAnimation.playAnimation()
    }
}
