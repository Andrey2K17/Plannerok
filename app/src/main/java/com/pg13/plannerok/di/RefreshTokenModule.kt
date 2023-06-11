package com.pg13.plannerok.di

import com.pg13.data.api.UserClient
import com.pg13.data.repositories.RefreshTokenRepositoryImpl
import com.pg13.domain.repositories.RefreshTokenRepository
import com.pg13.domain.usecases.PrefDataSourceUseCase
import com.pg13.domain.usecases.RefreshTokenUseCase
import com.pg13.plannerok.network.AuthAuthenticator
import com.pg13.plannerok.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RefreshTokenModule {

    @Singleton
    @Provides
    fun provideRefreshTokenRepository(client: UserClient): RefreshTokenRepository {
        return RefreshTokenRepositoryImpl(client)
    }

    @Singleton
    @Provides
    fun provideRefreshTokenUseCase(repository: RefreshTokenRepository): RefreshTokenUseCase {
        return RefreshTokenUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideAuthAuthenticator(
        prefDataSourceUseCase: PrefDataSourceUseCase,
        refreshTokenUseCase: Provider<RefreshTokenUseCase>
    ): AuthAuthenticator {
        return AuthAuthenticator(prefDataSourceUseCase, refreshTokenUseCase)
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(
        prefDataSourceUseCase: PrefDataSourceUseCase
    ): AuthInterceptor {
        return AuthInterceptor(prefDataSourceUseCase)
    }
}