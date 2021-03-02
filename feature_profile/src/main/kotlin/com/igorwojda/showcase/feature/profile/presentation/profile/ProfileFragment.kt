package com.igorwojda.showcase.feature.profile.presentation.profile

import com.igorwojda.showcase.base.delegate.viewBinding
import com.igorwojda.showcase.base.presentation.fragment.InjectionFragment
import com.igorwojda.showcase.feature.profile.R
import com.igorwojda.showcase.feature.profile.databinding.FragmentProfileBinding

class ProfileFragment : InjectionFragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        binding.underConstructionAnimation.playAnimation()
    }
}
