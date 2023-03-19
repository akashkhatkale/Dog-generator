package com.svg.dog_generator.domain.di

import com.svg.dog_generator.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGetRandomDogUseCase(
        impl: GetRandomDogUseCaseImpl
    ): GetRandomDogUseCase

    @Binds
    abstract fun bindGetAllDogsUseCase(
        impl: GetAllDogsUseCaseImpl
    ): GetAllDogsUseCase

    @Binds
    abstract fun bindClearAllDogsUseCase(
        impl: ClearAllDogsUseCaseImpl
    ): ClearAllDogsUseCase

}