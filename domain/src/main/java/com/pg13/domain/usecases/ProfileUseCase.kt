package com.pg13.domain.usecases

import com.pg13.domain.entities.Avatars
import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.Resource
import com.pg13.domain.entities.UpdateUserBodyDomain
import com.pg13.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileUseCase(private val repository: ProfileRepository) {

    fun getCurrentProfile(fromCache: Boolean): Flow<Resource<Profile?>> =
        repository.getCurrentProfile(fromCache)

    fun editProfile(profile: UpdateUserBodyDomain): Flow<Resource<Avatars>> =
        repository.editProfile(profile)
}