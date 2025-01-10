package com.menene.automarket.app.di

import com.menene.automarket.app.data.api.ApiService
import com.menene.automarket.app.data.repository.AutoRepositoryImpl
import com.menene.automarket.app.domain.repository.AutoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAutoRepository(apiService: ApiService): AutoRepository {
        return AutoRepositoryImpl(apiService)
    }
}