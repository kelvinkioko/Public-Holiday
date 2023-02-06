package com.holiday.util

sealed class Response<T>(
    val responseData: T? = null,
    val errorMessage: Any? = null
) {
    class Success<T>(responseData: T) : Response<T>(responseData = responseData)

    class Error<T>(responseData: T? = null, errorMessage: Any) :
        Response<T>(responseData = responseData, errorMessage = errorMessage)
}
