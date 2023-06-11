package com.pg13.plannerok.ui.features.register

import com.pg13.plannerok.R
import com.pg13.plannerok.databinding.FragmentRegisterBinding
import com.pg13.plannerok.ui.base.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : ViewBindingFragment<FragmentRegisterBinding>() {
    override fun getLayoutId() = R.layout.fragment_register

}