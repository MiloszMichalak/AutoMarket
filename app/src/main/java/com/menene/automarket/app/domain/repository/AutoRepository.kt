package com.menene.automarket.app.domain.repository

import com.menene.automarket.core.util.Result
import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.model.AutoFilter

interface AutoRepository {
    suspend fun getAutos(filter: AutoFilter): Result<List<Auto>, String>
    suspend fun getAuto(id: Int): Result<Auto, String>
}