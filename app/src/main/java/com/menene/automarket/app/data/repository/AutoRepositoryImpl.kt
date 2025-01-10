package com.menene.automarket.app.data.repository

import com.menene.automarket.app.data.api.ApiService
import com.menene.automarket.app.data.api.safeApiCall
import com.menene.automarket.core.util.Result
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.model.AutoFilter
import com.menene.automarket.app.domain.model.toDomain
import com.menene.automarket.app.domain.repository.AutoRepository
import javax.inject.Inject

class AutoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): AutoRepository {
    override suspend fun getAutos(filter: AutoFilter): Result<List<Auto>, String> {
        return safeApiCall {
            apiService.getAutos(
                brand = filter.brand,
                model = filter.model,
                minYear = filter.minYear,
                maxYear = filter.maxYear,
                minPrice = filter.minPrice,
                maxPrice = filter.maxPrice
            ).map { it.toDomain() }
        }
    }

    override suspend fun getAuto(id: Int): Result<Auto, String> {
        return safeApiCall {
            apiService.getAuto(id).toDomain()
        }
    }

}