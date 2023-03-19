package com.svg.dog_generator.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.svg.dog_generator.common.constants.DatabaseConstants.DOG_COLUMN_CREATED_AT
import com.svg.dog_generator.common.constants.DatabaseConstants.DOG_COLUMN_IMAGE_URL
import com.svg.dog_generator.common.constants.DatabaseConstants.DOG_COLUMN_STATUS
import com.svg.dog_generator.common.constants.DatabaseConstants.DOG_TABLE_NAME

@Entity(tableName = DOG_TABLE_NAME)
data class DogResponseModel(
    @PrimaryKey
    @ColumnInfo(name = DOG_COLUMN_IMAGE_URL, defaultValue = "")
    val imageUrl: String,
    @ColumnInfo(name = DOG_COLUMN_STATUS, defaultValue = "")
    val status: String,
    @ColumnInfo(name = DOG_COLUMN_CREATED_AT)
    val createdAt: Long
)
