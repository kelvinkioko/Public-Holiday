package com.holiday.util

sealed class Response<T>(
    val responseData: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(responseData: T) : Response<T>(responseData = responseData)

    class Error<T>(responseData: T? = null, errorMessage: String) :
        Response<T>(responseData = responseData, errorMessage = errorMessage)
}
