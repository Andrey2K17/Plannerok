package com.pg13.plannerok.ui.features.main

import androidx.lifecycle.ViewModel
import com.pg13.domain.usecases.PrefDataSourceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    prefDataSourceUseCase: PrefDataSourceUseCase
): ViewModel() {
    val authData = prefDataSourceUseCase.getAuthData()
}