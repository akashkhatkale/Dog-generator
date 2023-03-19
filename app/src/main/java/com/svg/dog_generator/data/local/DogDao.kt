package com.svg.dog_generator.data.local

import androidx.room.*
import com.svg.dog_generator.common.constants.DatabaseConstants
import com.svg.dog_generator.domain.entities.DogResponseModel

@Dao
interface DogDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(dog : DogResponseModel) : Long

    @Query("SELECT * FROM dogs ORDER BY ${DatabaseConstants.DOG_COLUMN_CREATED_AT} DESC")
    suspend fun getAllDogs() : List<DogResponseModel>

    @Query("DELETE FROM dogs")
    suspend fun clearDogs()

    @Delete
    suspend fun removeDog(dog: DogResponseModel)

}