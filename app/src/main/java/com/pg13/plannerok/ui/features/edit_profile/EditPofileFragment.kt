package com.pg13.plannerok.ui.features.edit_profile

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pg13.domain.entities.Resource
import com.pg13.plannerok.R
import com.pg13.plannerok.databinding.FragmentEditProfileBinding
import com.pg13.plannerok.ui.base.ViewBindingFragment
import com.pg13.plannerok.utils.getZodiacSign
import com.pg13.plannerok.utils.launchOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class EditProfileFragment : ViewBindingFragment<FragmentEditProfileBinding>() {
    override fun getLayoutId() = R.layout.fragment_edit_profile

    private val args: EditProfileFragmentArgs by navArgs()

    private val viewModel: EditProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewModel.setProfile(args.profile)

            launchOnLifecycle {
                viewModel.profileFlow.collect {
                    profile = it
                }
            }

            launchOnLifecycle {
                viewModel.avatars.collect { resources ->
                    when (resources) {
                        is Resource.Error -> {
                            progress.visibility = View.GONE
                            container.visibility = View.VISIBLE
                            Toast.makeText(requireContext(), resources.message, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is Resource.Loading -> {
                            progress.visibility = View.VISIBLE
                            container.visibility = View.GONE
                        }

                        is Resource.Success -> {

                            progress.visibility = View.GONE
                            container.visibility = View.VISIBLE

                            val bundle = bundleOf("loadProfile" to true)

                            setFragmentResult("edit_profile_key", bundle)

                            findNavController().navigateUp()
                        }
                    }
                }
            }

            sendBtn.setOnClick {
                if (checkFields()) {
                    viewModel.sendProfile()
                }
            }

            val myCalendar = Calendar.getInstance()

            val datePicker =
                DatePickerDialog.OnDateSetListener { datePicker, year, month, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, month)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateBirthdayAndZodiac(myCalendar)
                }

            birthdayEt.setOnClickListener {
                DatePickerDialog(
                    requireContext(),
                    datePicker,
                    myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }

    private fun updateBirthdayAndZodiac(calendar: Calendar) {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale("ru"))
        binding.birthdayEt.setText(sdf.format(calendar.time))
        val zodiac =
            getZodiacSign(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH))
        binding.zodiacSignEt.setText(zodiac)
    }

    private fun checkFields(): Boolean {
        if (binding.birthdayEt.text!!.isBlank()) {
            binding.birthdayTil.error = getString(R.string.required)
            return false
        }

        binding.birthdayTil.error = null
        return true
    }
}