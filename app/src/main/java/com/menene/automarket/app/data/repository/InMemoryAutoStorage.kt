package com.menene.automarket.app.data.repository

import com.menene.automarket.app.domain.model.Auto
import com.menene.automarket.app.domain.repository.AutoStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class InmMemoryAutoStorage: AutoStorage {
    private val storedAutos = MutableStateFlow(emptyList<Auto>())

    override fun saveAutos(autos: List<Auto>) {
        storedAutos.value = autos
    }

    override fun getAutos(): Flow<List<Auto>> {
        return storedAutos
    }

    override fun getAuto(index: Int): Flow<Auto> {
        return storedAutos.map { it[index] }
    }
}