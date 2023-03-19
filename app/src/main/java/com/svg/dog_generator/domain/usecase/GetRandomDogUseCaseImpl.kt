package com.svg.dog_generator.domain.usecase

import com.svg.dog_generator.common.data.Resource
import com.svg.dog_generator.domain.entities.DogResponseModel
import com.svg.dog_generator.domain.repository.DogRepository
import javax.inject.Inject

class GetRandomDogUseCaseImpl @Inject constructor(
    private val repo: DogRepository
): GetRandomDogUseCase {
    override suspend fun execute(): Resource<DogResponseModel> {
        return repo.getRandomDog()
    }
}