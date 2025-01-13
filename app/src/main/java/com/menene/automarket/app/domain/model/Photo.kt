package com.menene.automarket.app.domain.model

import com.menene.automarket.app.data.model.PhotoDto

data class Photo(
    val url: String
)

fun PhotoDto.toDomain(): Photo {
    return Photo(
        url = this.url
    )
}
