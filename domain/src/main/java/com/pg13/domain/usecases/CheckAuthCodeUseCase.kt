package com.pg13.domain.usecases

import com.pg13.domain.entities.CheckAuthCode
import com.pg13.domain.entities.Resource
import com.pg13.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class CheckAuthCodeUseCase(private val repository: UserRepository) {
    operator fun invoke(phone: String, code: String): Flow<Resource<CheckAuthCode>> =
        repository.checkAuthCode(phone, code)
}