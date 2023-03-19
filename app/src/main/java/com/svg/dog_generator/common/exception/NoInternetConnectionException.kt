package com.svg.dog_generator.common.exception

import com.svg.dog_generator.common.constants.ErrorConstants.INTERNET_CONNECTION_ERROR


class NoInternetConnectionException : Exception(INTERNET_CONNECTION_ERROR)