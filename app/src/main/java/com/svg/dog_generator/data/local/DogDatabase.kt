package com.svg.dog_generator.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.svg.dog_generator.domain.entities.DogResponseModel

@Database(
    entities = [DogResponseModel::class],
    version = 1
)
abstract class DogDatabase : RoomDatabase() {

    abstract fun getDogDao() : DogDAO

}