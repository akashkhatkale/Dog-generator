package com.svg.dog_generator.domain.usecase

import com.svg.dog_generator.common.data.Resource
import com.svg.dog_generator.domain.entities.DogResponseModel
import com.svg.dog_generator.domain.repository.DogRepository
import javax.inject.Inject

class ClearAllDogsUseCaseImpl @Inject constructor(
    private val repo: DogRepository
): ClearAllDogsUseCase {
    override suspend fun execute(): Resource<Boolean> {
        return repo.clearAllDogs()
    }
}