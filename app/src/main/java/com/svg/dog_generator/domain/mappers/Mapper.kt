package com.svg.dog_generator.domain.mappers

interface Mapper<in I, out O> {
    fun map(input: I) : O
}