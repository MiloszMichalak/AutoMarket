package com.menene.automarket.app.data.repository

import com.menene.automarket.app.data.api.ApiService
import com.menene.automarket.app.data.api.safeApiCall
import com.menene.automarket.app.util.Result
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.model.AutoFilter
import com.menene.automarket.app.domain.model.toDomain
import com.menene.automarket.app.domain.repository.AutoRepository
import com.menene.automarket.app.domain.repository.AutoStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AutoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val autoStorage: AutoStorage
): AutoRepository {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize(){
        scope.launch {
            when (val result = refresh()){
                is Result.Success -> autoStorage.saveAutos(result.data)
                is Result.Error -> Unit
            }
        }
    }

    suspend fun refresh(filter: AutoFilter? = null): Result<List<Auto>, String>{
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

    override fun getAutos(filter: AutoFilter?): Flow<List<Auto>> {
        return autoStorage.getAutos()
    }

    override fun getAuto(index: Int): Flow<Auto> {
        return autoStorage.getAuto(index)
    }

}