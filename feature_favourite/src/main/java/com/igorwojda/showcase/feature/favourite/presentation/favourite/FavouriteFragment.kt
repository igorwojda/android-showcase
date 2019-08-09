package com.igorwojda.showcase.feature.favourite.presentation.favourite

import com.igorwojda.base.presentation.fragment.BaseContainerFragment
import com.igorwojda.base.presentation.viewmodel.BaseViewModel
import com.igorwojda.base.presentation.viewmodel.UnneceryViewModel
import com.igorwojda.showcase.feature.favourite.R
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouriteFragment : BaseContainerFragment() {
    override val viewModel: BaseViewModel = UnneceryViewModel()

    override val layoutResourceId = R.layout.fragment_favourites

    override fun onResume() {
        super.onResume()
        underConstructionAnimation.playAnimation()
    }
}
