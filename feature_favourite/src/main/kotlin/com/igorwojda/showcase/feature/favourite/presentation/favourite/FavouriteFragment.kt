package com.igorwojda.showcase.feature.favourite.presentation.favourite

import androidx.fragment.app.Fragment
import com.igorwojda.showcase.base.delegate.viewBinding
import com.igorwojda.showcase.feature.favourite.R
import com.igorwojda.showcase.feature.favourite.databinding.FragmentFavouritesBinding

class FavouriteFragment : Fragment(R.layout.fragment_favourites) {

    private val binding: FragmentFavouritesBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        binding.underConstructionAnimation.playAnimation()
    }
}
