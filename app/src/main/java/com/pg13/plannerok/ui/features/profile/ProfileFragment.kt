package com.pg13.plannerok.ui.features.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pg13.domain.entities.Resource
import com.pg13.plannerok.R
import com.pg13.plannerok.databinding.FragmentProfileBinding
import com.pg13.plannerok.mappers.mapToUI
import com.pg13.plannerok.ui.base.ViewBindingFragment
import com.pg13.plannerok.utils.launchOnLifecycle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : ViewBindingFragment<FragmentProfileBinding>() {
    override fun getLayoutId() = R.layout.fragment_profile

    private val viewModel: ProfileViewModel by activityViewModels {
        defaultViewModelProviderFactory
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            refreshLayout.setOnRefreshListener {
                viewModel.getProfile(false)
            }

            editBtn.setOnClick {
                profile?.let {
                    findNavController().navigate(
                        ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(
                            it
                        )
                    )
                }
            }

            launchOnLifecycle {
                viewModel.getProfileEvent.collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            refreshLayout.isRefreshing = false
                            Toast.makeText(requireContext(), resource.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is Resource.Loading -> {
                            refreshLayout.isRefreshing = true
                        }

                        is Resource.Success -> {
                            refreshLayout.isRefreshing = false
                            resource.data?.mapToUI()?.let { profile = it } ?: {
                                viewModel.getProfile(false)
                            }
                        }
                    }
                }
            }

            parentFragmentManager.setFragmentResultListener(
                "edit_profile_key", this@ProfileFragment
            ) { _, result ->
                val loadProfile = result.getBoolean("loadProfile")
                if (loadProfile) {
                    viewModel.getProfile(false)
                }
            }
        }


    }
}