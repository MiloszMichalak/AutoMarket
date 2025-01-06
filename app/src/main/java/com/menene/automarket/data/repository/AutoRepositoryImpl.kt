package com.menene.automarket.data.repository

import com.menene.automarket.data.api.ApiService
import com.menene.automarket.data.model.AutoDto
import com.menene.automarket.domain.model.Auto
import com.menene.automarket.domain.model.toDomain
import com.menene.automarket.domain.repository.AutoRepository
import javax.inject.Inject

class AutoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): AutoRepository {
    override suspend fun getAutos(
        brand: String?,
        model: String?,
        minYear: Short?,
        maxYear: Short?,
        minPrice: UInt?,
        maxPrice: UInt?
    ): List<Auto> {
        return apiService.getAutos().map { it.toDomain() }
    }
}