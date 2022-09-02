package com.igorwojda.showcase.base.domain.result

public inline fun <T> Result<T>.mapSuccess(
    crossinline onResult: Result.Success<T>.() -> Result<T>,
): Result<T> {
    if (this is Result.Success) {
        return onResult(this)
    }
    return this
}
