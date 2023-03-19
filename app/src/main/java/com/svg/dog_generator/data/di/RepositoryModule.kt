package com.svg.dog_generator.data.di

import com.svg.dog_generator.data.repository.DogRepositoryImpl
import com.svg.dog_generator.domain.repository.DogRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDogRepository(
        impl: DogRepositoryImpl
    ): DogRepository

}