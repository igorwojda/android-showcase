package com.igorwojda.showcase.feature.profile.presentation.profile

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.igorwojda.showcase.feature.profile.R
import com.igorwojda.showcase.feature.profile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()

    override fun onResume() {
        super.onResume()
        binding.underConstructionAnimation.playAnimation()
    }
}
