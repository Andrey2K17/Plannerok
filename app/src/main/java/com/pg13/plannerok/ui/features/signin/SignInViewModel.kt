package com.pg13.plannerok.ui.features.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pg13.domain.entities.AuthDataDomain
import com.pg13.domain.usecases.CheckAuthCodeUseCase
import com.pg13.domain.usecases.PrefDataSourceUseCase
import com.pg13.domain.usecases.SendAuthCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val sendAuthCodeUseCase: SendAuthCodeUseCase,
    private val checkAuthCodeUseCase: CheckAuthCodeUseCase,
    private val prefDataSourceUseCase: PrefDataSourceUseCase
) : ViewModel() {

    val phone = MutableStateFlow("")
    val code = MutableStateFlow("")

    private val sendButtonAuthCodePressedEvent = MutableSharedFlow<String>()
    val confirmationCodeSentEvent = sendButtonAuthCodePressedEvent.flatMapLatest { phone ->
        sendAuthCodeUseCase.invoke(phone)
    }

    fun sendAuthCode() {
        viewModelScope.launch {
            sendButtonAuthCodePressedEvent.emit(phone.value)
        }
    }

    private val sendButtonCheckCodePressedEvent = MutableSharedFlow<String>()
    val confirmationCodeCheckEvent = sendButtonCheckCodePressedEvent.flatMapLatest { code ->
        checkAuthCodeUseCase.invoke(phone.value, code)
    }

    fun checkAuthCode() {
        viewModelScope.launch {
            sendButtonCheckCodePressedEvent.emit(code.value)
        }
    }

    fun setAuthData(authDataDomain: AuthDataDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            prefDataSourceUseCase.setAuthData(authDataDomain)
        }
    }

}