package com.igorwojda.showcase.feature.profile.presentation.profile

import com.igorwojda.showcase.feature.profile.R
import com.igorwojda.showcase.library.base.presentation.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseContainerFragment() {

    override val layoutResourceId = R.layout.fragment_profile

    override fun onResume() {
        super.onResume()
        underConstructionAnimation.playAnimation()
    }
}
