package com.pg13.domain.usecases

import com.pg13.domain.entities.RegisterData
import com.pg13.domain.entities.Resource
import com.pg13.domain.repositories.RegisterRepository
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(private val repository: RegisterRepository) {
    operator fun invoke(
        phone: String,
        name: String,
        userName: String
    ): Flow<Resource<RegisterData>> = repository.register(phone, name, userName)

}