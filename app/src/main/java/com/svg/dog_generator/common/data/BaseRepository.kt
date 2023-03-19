package com.svg.dog_generator.common.data

import android.util.Log
import com.svg.dog_generator.common.exception.NoInternetConnectionException
import com.svg.dog_generator.common.exception.UnknownException
import com.svg.dog_generator.domain.mappers.Mapper
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    open suspend fun <T, S> safeApiCall(api: suspend () -> Response<T>, mapper: Mapper<T, S>): Resource<S> {
        try {
            val result = api()
            if (result.isSuccessful) {
                result.body()?.let { body ->
                    return Resource.Success(mapper.map(body))
                }
            }
            return Resource.Error(UnknownException())
        } catch (e: IOException) {
            return Resource.Error(NoInternetConnectionException())
        } catch (e: HttpException) {
            return Resource.Error(e)
        }
    }

}