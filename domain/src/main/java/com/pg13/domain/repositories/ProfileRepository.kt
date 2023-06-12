package com.pg13.domain.repositories

import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.Resource
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getCurrentProfile(): Flow<Resource<Profile>>
}