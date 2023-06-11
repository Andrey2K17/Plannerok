package com.pg13.plannerok.ui.features.profile

import com.pg13.plannerok.R
import com.pg13.plannerok.databinding.FragmentProfileBinding
import com.pg13.plannerok.ui.base.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : ViewBindingFragment<FragmentProfileBinding>() {
    override fun getLayoutId() = R.layout.fragment_profile
}