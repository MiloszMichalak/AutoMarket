package com.menene.automarket.app.domain.model

data class AutoFilter(
    val brand: String? = null,
    val model: String? = null,
    val minYear: Short? = null,
    val maxYear: Short? = null,
    val minPrice: UInt? = null,
    val maxPrice: UInt? = null
)
