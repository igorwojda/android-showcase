package com.igorwojda.showcase.base.domain.result

sealed interface Result<out T> {
    data class Success<T>(val value: T) : Result<T>
    data class Failure(val throwable: Throwable? = null) : Result<Nothing>
}
