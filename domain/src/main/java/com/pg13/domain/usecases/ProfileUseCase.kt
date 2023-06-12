package com.pg13.domain.usecases

import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.Resource
import com.pg13.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileUseCase(private val repository: ProfileRepository) {

    fun getCurrentProfile(name: String): Flow<Resource<Profile>> = repository.getCurrentProfile()
}