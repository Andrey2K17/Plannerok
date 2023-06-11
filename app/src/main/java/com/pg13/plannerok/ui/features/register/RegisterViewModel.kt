package com.pg13.plannerok.ui.features.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg13.domain.usecases.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    val phone = MutableStateFlow("")
    val name = MutableStateFlow("")
    val userName = MutableStateFlow("")


    private val sendButtonPressedEvent = MutableSharedFlow<Triple<String, String, String>>()
    val registerEvent = sendButtonPressedEvent.flatMapLatest { triple ->
        registerUseCase.invoke(triple.first, triple.second, triple.third)
    }

    fun register() {
        viewModelScope.launch {
            sendButtonPressedEvent.emit(Triple(phone.value, name.value, userName.value))
        }
    }

}