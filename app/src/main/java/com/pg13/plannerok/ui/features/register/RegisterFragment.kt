package com.pg13.plannerok.ui.features.register

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.pg13.domain.entities.Resource
import com.pg13.plannerok.R
import com.pg13.plannerok.databinding.FragmentRegisterBinding
import com.pg13.plannerok.ui.base.ViewBindingFragment
import com.pg13.plannerok.utils.launchOnLifecycle
import com.pg13.plannerok.utils.userNameValidation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : ViewBindingFragment<FragmentRegisterBinding>() {
    override fun getLayoutId() = R.layout.fragment_register

    private val args: RegisterFragmentArgs by navArgs()

    private val viewModel: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            phoneEt.setText(args.phone)
            sendBtn.setOnClick {
                if (checkAllFields()) {
                    viewModel.phone.value = args.phone
                    viewModel.name.value = nameEt.text.toString()
                    viewModel.userName.value = userNameEt.text.toString()
                    viewModel.register()
                }
            }
        }

        viewLifecycleOwner.launchOnLifecycle {
            viewModel.registerEvent.collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        Log.d("test123", "success: ${resource.data}")
                    }
                }
            }
        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.nameEt.text!!.isBlank()) {
            binding.nameTil.error = getString(R.string.required)
            return false
        }

        if (binding.userNameEt.text!!.isBlank()) {
            binding.userNameTil.error = getString(R.string.required)
            return false
        }

        if (!binding.userNameEt.text.toString().userNameValidation()) {
            binding.userNameTil.error = getString(R.string.field_special_character)
            return false
        }

        binding.nameTil.error = null
        binding.userNameTil.error = null
        return true
    }
}