package com.pg13.plannerok.di

import com.pg13.data.api.UserClient
import com.pg13.data.db.Database
import com.pg13.data.db.ProfileDao
import com.pg13.data.db.profile.CacheDataSourceProfile
import com.pg13.data.net.profile.CloudDataSourceEditProfile
import com.pg13.data.net.profile.CloudDataSourceProfile
import com.pg13.data.repositories.ProfileRepositoryImpl
import com.pg13.domain.repositories.ProfileRepository
import com.pg13.domain.usecases.ProfileUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProfileModule {
    @Singleton
    @Provides
    fun provideQueryDao(database: Database): ProfileDao {
        return database.profileDao()
    }

    @Singleton
    @Provides
    fun provideProfileCacheDataSource(dao: ProfileDao): CacheDataSourceProfile {
        return CacheDataSourceProfile(dao)
    }

    @Singleton
    @Provides
    fun provideProfileCloudDataSource(
        client: UserClient,
        cacheDataSourceProfile: CacheDataSourceProfile
    ): CloudDataSourceProfile {
        return CloudDataSourceProfile(client, cacheDataSourceProfile)
    }

    @Singleton
    @Provides
    fun provideEditProfileCloudDataSource(client: UserClient): CloudDataSourceEditProfile {
        return CloudDataSourceEditProfile(client)
    }

    @Singleton
    @Provides
    fun provideProfileRepository(
        cloudDataSourceProfile: CloudDataSourceProfile,
        cacheDataSourceProfile: CacheDataSourceProfile,
        cloudDataSourceEditProfile: CloudDataSourceEditProfile
    ): ProfileRepository {
        return ProfileRepositoryImpl(
            cloudDataSourceProfile,
            cacheDataSourceProfile,
            cloudDataSourceEditProfile
        )
    }

    @Singleton
    @Provides
    fun provideProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase {
        return ProfileUseCase((profileRepository))
    }
}