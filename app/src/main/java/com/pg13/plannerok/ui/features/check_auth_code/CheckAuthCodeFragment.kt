package com.pg13.plannerok.ui.features.check_auth_code

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fraggjkee.smsconfirmationview.SmsConfirmationView
import com.pg13.domain.entities.AuthDataDomain
import com.pg13.domain.entities.Resource
import com.pg13.plannerok.R
import com.pg13.plannerok.databinding.FragmentCheckAuthCodeBinding
import com.pg13.plannerok.ui.base.ViewBindingFragment
import com.pg13.plannerok.ui.features.signin.SignInViewModel
import com.pg13.plannerok.utils.launchOnLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckAuthCodeFragment : ViewBindingFragment<FragmentCheckAuthCodeBinding>() {
    override fun getLayoutId() = R.layout.fragment_check_auth_code

    private val viewModel: SignInViewModel by activityViewModels {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            smsCodeView.onChangeListener =
                SmsConfirmationView.OnChangeListener { code, isComplete ->
                    viewModel.code.value = code
                    if (isComplete) viewModel.checkAuthCode()
                }

            viewLifecycleOwner.launchOnLifecycle {
                viewModel.confirmationCodeCheckEvent.collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            if (resource.data.isUserExists) {
                                viewModel.setAuthData(AuthDataDomain(resource.data.refreshToken, resource.data.accessToken))
                                findNavController().navigate(CheckAuthCodeFragmentDirections.actionCheckAuthCodeFragmentToProfileFragment())
                            } else {
                                findNavController().navigate(
                                    CheckAuthCodeFragmentDirections.actionCheckAuthCodeFragmentToRegisterFragment(
                                        viewModel.phone.value
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}