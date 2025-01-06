package com.menene.automarket.domain.repository

import com.menene.automarket.domain.model.Auto

interface AutoRepository {
    suspend fun getAutos(
        brand: String? = null,
        model: String? = null,
        minYear: Short? = null,
        maxYear: Short? = null,
        minPrice: UInt? = null,
        maxPrice: UInt? = null
    ): List<Auto>
}