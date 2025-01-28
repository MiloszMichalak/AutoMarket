package com.menene.automarket.app.data.repository

import com.menene.automarket.app.data.api.ApiService
import com.menene.automarket.app.data.api.safeApiCall
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.model.AutoFilter
import com.menene.automarket.app.domain.model.toDomain
import com.menene.automarket.app.domain.repository.AutoRepository
import com.menene.automarket.app.domain.repository.AutoStorage
import com.menene.automarket.app.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AutoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val autoStorage: AutoStorage
) : AutoRepository {

    override suspend fun refresh(): Result<Unit, String> {
        when (val result = getAutosFromApi()) {
            is Result.Success -> {
                autoStorage.saveAutos(result.data)
                return Result.Success(Unit)
            }
            is Result.Error -> {
                return Result.Error(result.error)
            }
        }
    }

    override suspend fun getAutosFromApi(filter: AutoFilter?): Result<List<Auto>, String> {
        return safeApiCall {
            apiService.getAutos(
                brand = filter?.brand,
                model = filter?.model,
                minYear = filter?.minYear,
                maxYear = filter?.maxYear,
                minPrice = filter?.minPrice,
                maxPrice = filter?.maxPrice
            ).map { it.toDomain() }
        }
    }

    override fun getAutos(): Flow<List<Auto>> {
        return autoStorage.getAutos()
    }

    override fun getAuto(index: Int): Flow<Auto> {
        return autoStorage.getAuto(index)
    }

}