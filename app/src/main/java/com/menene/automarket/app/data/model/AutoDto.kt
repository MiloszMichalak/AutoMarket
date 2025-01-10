package com.menene.automarket.app.data.model

data class AutoDto(
    val id: String,
    val brand: String,
    val model: String,
    val year: Short,
    val price: UInt,
    val url: String
)
