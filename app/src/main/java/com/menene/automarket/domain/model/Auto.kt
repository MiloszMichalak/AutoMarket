package com.menene.automarket.domain.model

import com.menene.automarket.data.model.AutoDto

data class Auto(
    val id: String,
    val brand: String,
    val model: String,
    val year: String,
    val price: String,
    val url: String
)

fun AutoDto.toDomain(): Auto{
    return Auto(
        id = this.id,
        brand = this.brand,
        model = this.model,
        year = this.year.toString(),
        price = this.price.toString(),
        url = this.url
    )
}