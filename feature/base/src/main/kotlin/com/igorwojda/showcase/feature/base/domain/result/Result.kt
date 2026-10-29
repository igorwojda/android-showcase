package com.igorwojda.showcase.feature.base.domain.result

import com.igorwojda.showcase.feature.base.domain.error.AppError

sealed interface Result<out T> {
    data class Success<T>(
        val value: T,
    ) : Result<T>

    data class Failure(
        val error: AppError = AppError.Unknown,
    ) : Result<Nothing>
}
