package com.svg.dog_generator.domain.usecase

import com.svg.dog_generator.common.data.Resource
import com.svg.dog_generator.domain.entities.DogResponseModel

interface GetAllDogsUseCase: SuspendingUseCase<Resource<List<DogResponseModel>>>