package com.menene.automarket.di

import com.menene.automarket.data.api.ApiService
import com.menene.automarket.data.repository.AutoRepositoryImpl
import com.menene.automarket.domain.repository.AutoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://autoselling.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAutoRepository(apiService: ApiService): AutoRepository {
        return AutoRepositoryImpl(apiService)
    }
}