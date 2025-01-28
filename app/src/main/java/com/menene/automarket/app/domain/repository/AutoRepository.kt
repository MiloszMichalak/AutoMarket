package com.menene.automarket.app.domain.repository

import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.model.AutoFilter
import com.menene.automarket.app.util.Result
import kotlinx.coroutines.flow.Flow

interface AutoRepository {
    suspend fun refresh(): Result<Unit, String>
    suspend fun getAutosFromApi(filter: AutoFilter? = null): Result<List<Auto>, String>
    fun getAutos(): Flow<List<Auto>>
    fun getAuto(index: Int): Flow<Auto>
}