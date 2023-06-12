package com.pg13.plannerok.ui.features.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg13.domain.usecases.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    init {
        getProfile(true)
    }

    private val getProfilePressedEvent = MutableSharedFlow<Boolean>(1)
    val getProfileEvent = getProfilePressedEvent.flatMapLatest { fromCache ->
        profileUseCase.getCurrentProfile(fromCache)
    }

    fun getProfile(fromCache: Boolean) {
        viewModelScope.launch {
            getProfilePressedEvent.emit(fromCache)
            cancel()
        }
    }

}