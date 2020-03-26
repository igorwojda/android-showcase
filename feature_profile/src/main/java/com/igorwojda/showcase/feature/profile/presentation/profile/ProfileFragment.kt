package com.igorwojda.showcase.feature.profile.presentation.profile

import android.view.View
import com.igorwojda.showcase.feature.profile.R
import com.igorwojda.showcase.feature.profile.databinding.FragmentProfileBinding
import com.igorwojda.showcase.library.base.presentation.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseContainerFragment<FragmentProfileBinding>() {

    override val layoutResourceId = R.layout.fragment_profile

    override fun setupBinding(view: View) {
        // TODO Create ProfileViewModel
    }

    override fun onResume() {
        super.onResume()
        underConstructionAnimation.playAnimation()
    }
}
