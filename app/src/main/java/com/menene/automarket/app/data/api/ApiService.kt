package com.menene.automarket.app.data.api

import com.menene.automarket.BuildConfig
import com.menene.automarket.app.data.model.AutoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(BuildConfig.BASE_REPOSITORY)
    suspend fun getAutos(
        @Query("brand") brand: String? = null,
        @Query("model") model: String? = null,
        @Query("minYear") minYear: Short? = null,
        @Query("maxYear") maxYear: Short? = null,
        @Query("minPrice") minPrice: UInt? = null,
        @Query("maxPrice") maxPrice: UInt? = null,
    ): List<AutoDto>

    @GET(BuildConfig.BASE_REPOSITORY)
    suspend fun getAuto(
        @Query("id") id: Int
    ): List<AutoDto>
}