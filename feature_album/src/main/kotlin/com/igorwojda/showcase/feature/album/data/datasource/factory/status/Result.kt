package com.igorwojda.showcase.feature.album.data.datasource.factory.status

sealed interface Result<T> {

    /**
     * Represents a network result that successfully received a response containing body data.
     */
    class Success<T>(val data: T) : Result<T>

    /**
     * Represents a network result that successfully received a response containing an error message.
     */
    class Error<T>(val code: Int, val message: String?) : Result<T>

    /**
     * Represents a network result that faced an unexpected exception before getting a response
     * from the network such as IOException and UnKnownHostException.
     */
    class Exception<T>(val e: Throwable) : Result<T>
}
