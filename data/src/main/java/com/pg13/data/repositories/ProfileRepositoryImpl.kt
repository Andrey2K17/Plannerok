package com.pg13.data.repositories

import com.pg13.data.db.profile.CacheDataSourceProfile
import com.pg13.data.mappers.mapToData
import com.pg13.data.net.profile.CloudDataSourceEditProfile
import com.pg13.data.net.profile.CloudDataSourceProfile
import com.pg13.domain.entities.Avatars
import com.pg13.domain.entities.Profile
import com.pg13.domain.entities.Resource
import com.pg13.domain.entities.UpdateUserBodyDomain
import com.pg13.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow

class ProfileRepositoryImpl(
    private val cloudDataSourceProfile: CloudDataSourceProfile,
    private val cacheDataSourceProfile: CacheDataSourceProfile,
    private val cloudDataSourceEditProfile: CloudDataSourceEditProfile
) : ProfileRepository {
    override fun getCurrentProfile(fromCache: Boolean): Flow<Resource<Profile?>> {
        return if (fromCache) cacheDataSourceProfile.getProfile() else cloudDataSourceProfile.getCurrentProfile()
    }

    override fun editProfile(profile: UpdateUserBodyDomain): Flow<Resource<Avatars>> {
        return cloudDataSourceEditProfile.editProfile(profile.mapToData())
    }
}