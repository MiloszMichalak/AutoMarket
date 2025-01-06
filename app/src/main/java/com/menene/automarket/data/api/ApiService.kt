package com.menene.automarket.data.api

import com.menene.automarket.BuildConfig
import com.menene.automarket.data.model.AutoDto
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
}