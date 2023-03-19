package com.svg.dog_generator.domain.mappers

import com.svg.dog_generator.data.dto.DogResponseDto
import com.svg.dog_generator.domain.entities.DogResponseModel
import java.util.Calendar

object DogMapper: Mapper<DogResponseDto, DogResponseModel> {
    override fun map(input: DogResponseDto): DogResponseModel {
        return DogResponseModel(
            imageUrl = input.imageUrl.orEmpty(),
            status = input.status.orEmpty(),
            createdAt = Calendar.getInstance().timeInMillis
        )
    }
}