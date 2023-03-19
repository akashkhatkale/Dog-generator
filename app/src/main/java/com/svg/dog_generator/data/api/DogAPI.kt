package com.svg.dog_generator.data.api

import com.svg.dog_generator.data.dto.DogResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface DogAPI {

    @GET("/api/breeds/image/random")
    suspend fun getRandomDog(): Response<DogResponseDto>

}