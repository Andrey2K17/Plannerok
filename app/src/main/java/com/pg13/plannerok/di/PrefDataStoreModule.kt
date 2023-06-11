package com.pg13.plannerok.di

import android.content.Context
import com.pg13.data.prefs.PrefDataSource
import com.pg13.data.repositories.PrefDataSourceRepositoryImpl
import com.pg13.domain.repositories.PrefDatSourceRepository
import com.pg13.domain.usecases.PrefDataSourceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PrefDataStoreModule {

    @Singleton
    @Provides
    fun providePrefDataSource(@ApplicationContext context: Context): PrefDataSource {
        return PrefDataSource(context)
    }

    @Singleton
    @Provides
    fun providePrefRepository(
        prefDataSource: PrefDataSource,
    ): PrefDatSourceRepository {
        return PrefDataSourceRepositoryImpl(prefDataSource)
    }

    @Provides
    fun providePrefDataSourceUseCase(repository: PrefDatSourceRepository): PrefDataSourceUseCase {
        return PrefDataSourceUseCase(repository)
    }
}