package com.pg13.data.repositories

import com.pg13.data.net.profile.CloudDataSourceProfile
import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.Resource
import com.pg13.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val cloudDataSourceProfile: CloudDataSourceProfile
) : ProfileRepository {
    override fun getCurrentProfile(): Flow<Resource<Profile>> {
        return cloudDataSourceProfile.getCurrentProfile()
    }
}