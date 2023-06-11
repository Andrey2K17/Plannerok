package com.pg13.domain.usecases

import com.pg13.domain.entities.IsSuccess
import com.pg13.domain.entities.Resource
import com.pg13.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow

class SendAuthCodeUseCase(private val repository: UserRepository) {
    operator fun invoke(phone: String): Flow<Resource<IsSuccess>> = repository.sendAuthCode(phone)
}