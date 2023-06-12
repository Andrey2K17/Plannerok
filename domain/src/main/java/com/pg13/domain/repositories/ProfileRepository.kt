package com.pg13.domain.repositories

import com.pg13.domain.entities.Avatars
import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.Resource
import com.pg13.domain.entities.UpdateUserBodyDomain
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getCurrentProfile(fromCache: Boolean): Flow<Resource<Profile?>>

    fun editProfile(profile: UpdateUserBodyDomain): Flow<Resource<Avatars>>
}