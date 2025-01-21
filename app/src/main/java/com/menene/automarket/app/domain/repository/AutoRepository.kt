package com.menene.automarket.app.domain.repository

import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.model.AutoFilter
import kotlinx.coroutines.flow.Flow

interface AutoRepository {
    fun getAutos(filter: AutoFilter? = null): Flow<List<Auto>>
    fun getAuto(index: Int): Flow<Auto>
}