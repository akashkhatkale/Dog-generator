package com.svg.dog_generator.domain.repository

import com.svg.dog_generator.common.data.Resource
import com.svg.dog_generator.domain.entities.DogResponseModel

interface DogRepository {

    suspend fun getRandomDog(): Resource<DogResponseModel>

    suspend fun getAllDogs(): Resource<List<DogResponseModel>>

    suspend fun clearAllDogs(): Resource<Boolean>
}