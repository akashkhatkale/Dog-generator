package com.svg.dog_generator.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogResponseDto(
    @Json(name = "message") val imageUrl: String?,
    @Json(name = "status") val status: String?
)