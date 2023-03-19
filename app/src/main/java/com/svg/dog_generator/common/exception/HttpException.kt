package com.svg.dog_generator.common.exception

import com.svg.dog_generator.common.constants.ErrorConstants.HTTP_ERROR

class HttpException(
    val statusCode : Int
) : Exception(HTTP_ERROR)