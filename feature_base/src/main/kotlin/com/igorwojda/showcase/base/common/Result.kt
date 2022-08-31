package com.igorwojda.showcase.base.common

sealed interface Result<out T> {
    data class Success<T>(val value: T) : Result<T>
    data class Failure(val cause: Throwable) : Result<Nothing>
}
