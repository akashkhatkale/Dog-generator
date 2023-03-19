package com.svg.dog_generator.domain.usecase

import com.svg.dog_generator.common.data.Resource

interface ClearAllDogsUseCase: SuspendingUseCase<Resource<Boolean>>