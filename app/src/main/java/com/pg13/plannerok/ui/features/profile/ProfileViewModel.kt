package com.pg13.plannerok.ui.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg13.domain.usecases.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
) : ViewModel() {

    private val getProfilePressedEvent = MutableSharedFlow<String>()
    val getProfileEvent = getProfilePressedEvent.flatMapLatest { name ->
        profileUseCase.getCurrentProfile(name)
    }

    fun getProfile() {
        viewModelScope.launch {
            getProfilePressedEvent.emit("asd")
        }
    }

}