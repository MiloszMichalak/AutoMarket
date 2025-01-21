package com.menene.automarket.app.domain.repository

import com.menene.automarket.app.domain.model.Auto
import kotlinx.coroutines.flow.Flow

interface AutoStorage {
    fun saveAutos(autos: List<Auto>)
    fun getAutos(): Flow<List<Auto>>
    fun getAuto(index: Int): Flow<Auto>
}