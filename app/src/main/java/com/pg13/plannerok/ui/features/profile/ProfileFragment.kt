package com.pg13.plannerok.ui.features.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.pg13.domain.entities.Resource
import com.pg13.plannerok.R
import com.pg13.plannerok.databinding.FragmentProfileBinding
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
            sendBtn.setOnClick {
                viewModel.getProfile()
            }
        }

        launchOnLifecycle {
            viewModel.getProfileEvent.collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.d("test123", "error: ${resource.message}")
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        Log.d("test123", "success resource: ${resource.data}")
                    }
                }
            }
        }
    }
}