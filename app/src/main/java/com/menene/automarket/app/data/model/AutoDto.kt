package com.menene.automarket.app.data.model

data class AutoDto(
    val id: UInt,
    val brand: String,
    val model: String,
    val year: Short,
    val price: UInt,
    val course: UInt,
    val photos: List<PhotoDto>
)
