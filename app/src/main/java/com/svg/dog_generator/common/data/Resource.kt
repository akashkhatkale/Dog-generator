package com.svg.dog_generator.common.data

sealed class Resource<T>(
    val data : T? = null,
    val message : Exception? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: Exception, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
    class Idle<T> : Resource<T>()
}