package com.pg13.plannerok.ui.features.edit_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg13.domain.usecases.ProfileUseCase
import com.pg13.plannerok.entities.ProfileUI
import com.pg13.plannerok.mappers.mapToDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    val profileFlow = MutableStateFlow<ProfileUI?>(null)

    fun setProfile(profile: ProfileUI) {
        profileFlow.value = profile
    }


    private val sendProfileEvent = MutableSharedFlow<Unit>(1)
    val avatars = sendProfileEvent
        .flatMapLatest {
            profileFlow.value?.let { }
            profileUseCase.editProfile(profileFlow.value!!.mapToDomain())
        }

    fun sendProfile() {
        viewModelScope.launch {
            sendProfileEvent.emit(Unit)
            cancel()
        }
    }
}