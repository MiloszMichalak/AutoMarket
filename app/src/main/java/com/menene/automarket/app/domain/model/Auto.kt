package com.menene.automarket.app.domain.model

import com.menene.automarket.app.data.model.AutoDto

data class Auto(
    val id: String,
    val brand: String,
    val model: String,
    val year: String,
    val price: String,
    val course: String,
    val photos: List<Photo>
)

fun AutoDto.toDomain(): Auto {
    return Auto(
        id = this.id.toString(),
        brand = this.brand,
        model = this.model,
        year = this.year.toString(),
        price = this.price.toString(),
        course = this.course.toString(),
        photos = this.photos.map { it.toDomain() }
    )
}