package com.svg.dog_generator.domain.usecase

interface SuspendingUseCase<out O> {
    suspend fun execute() : O
}