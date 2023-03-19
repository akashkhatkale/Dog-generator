package com.svg.dog_generator.data.repository

import com.svg.dog_generator.common.constants.DatabaseConstants.DOG_TABLE_CAPACITY
import com.svg.dog_generator.common.data.BaseRepository
import com.svg.dog_generator.common.data.Resource
import com.svg.dog_generator.data.api.DogAPI
import com.svg.dog_generator.data.local.DogDAO
import com.svg.dog_generator.domain.entities.DogResponseModel
import com.svg.dog_generator.domain.mappers.DogMapper
import com.svg.dog_generator.domain.repository.DogRepository
import java.util.*
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val api: DogAPI,
    private val dao: DogDAO
): DogRepository, BaseRepository() {

    private suspend fun insertIntoDB(resource: Resource<DogResponseModel>) {
        when (resource) {
            is Resource.Success -> {
                resource.data?.let {
                    val allDogs = dao.getAllDogs()
                    if (allDogs.size >= DOG_TABLE_CAPACITY) {
                        val lastDog = allDogs.last()
                        dao.removeDog(lastDog)
                    }
                    dao.upsert(it)
                }
            }
            else -> Unit
        }
    }

    override suspend fun getRandomDog(): Resource<DogResponseModel> {
        val result = safeApiCall({ api.getRandomDog() }, DogMapper)
        insertIntoDB(result)

        return result
    }

    override suspend fun getAllDogs(): Resource<List<DogResponseModel>> {
        return try {
            val result = dao.getAllDogs()
            println(result)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(Exception(e.localizedMessage))
        }
    }

    override suspend fun clearAllDogs(): Resource<Boolean> {
        return try {
            dao.clearDogs()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(Exception(e.localizedMessage))
        }
    }
}