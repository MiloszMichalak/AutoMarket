package com.menene.automarket.app.di

import com.menene.automarket.app.domain.repository.AutoRepository
import com.menene.automarket.app.domain.use_case.GetAutoUseCase
import com.menene.automarket.app.domain.use_case.GetAutosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetAutosUseCase(autoRepository: AutoRepository): GetAutosUseCase {
        return GetAutosUseCase(autoRepository)
    }

    @Provides
    @Singleton
    fun provideGetAutoCase(autoRepository: AutoRepository): GetAutoUseCase {
        return GetAutoUseCase(autoRepository)
    }
}