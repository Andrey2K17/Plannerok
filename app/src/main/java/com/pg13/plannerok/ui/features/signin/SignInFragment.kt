package com.pg13.plannerok.ui.features.signin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pg13.domain.entities.Resource
import com.pg13.plannerok.R
import com.pg13.plannerok.databinding.FragmentSignInBinding
import com.pg13.plannerok.ui.base.ViewBindingFragment
import com.pg13.plannerok.utils.launchOnLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : ViewBindingFragment<FragmentSignInBinding>() {
    override fun getLayoutId() = R.layout.fragment_sign_in

    private val viewModel: SignInViewModel by activityViewModels {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            ccp.registerCarrierNumberEditText(editTextCarrierNumber)
            ccp.setPhoneNumberValidityChangeListener {
                sendBtn.setButtonEnabled(it)
                if (it) viewModel.phone.value = ccp.fullNumberWithPlus
            }
            sendBtn.setOnClick {
                viewModel.sendAuthCode()
            }

            launchOnLifecycle {
                viewModel.confirmationCodeSentEvent.collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            sendBtn.showProgress(false)
                            Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is Resource.Loading -> {
                            sendBtn.showProgress(true)
                        }

                        is Resource.Success -> {
                            editTextCarrierNumber.clearFocus()
                            sendBtn.showProgress(false)
                            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToCheckAuthCodeFragment())
                        }
                    }
                }
            }
        }
    }
}