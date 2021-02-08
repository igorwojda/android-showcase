package com.igorwojda.showcase.feature.favourite.presentation.favourite

import com.igorwojda.showcase.base.delegate.viewBinding
import com.igorwojda.showcase.base.presentation.fragment.InjectionFragment
import com.igorwojda.showcase.feature.favourite.databinding.FragmentFavouritesBinding

class FavouriteFragment : InjectionFragment() {

    private val binding by viewBinding(FragmentFavouritesBinding::bind)

    override fun onResume() {
        super.onResume()
        binding.underConstructionAnimation.playAnimation()
    }
}
