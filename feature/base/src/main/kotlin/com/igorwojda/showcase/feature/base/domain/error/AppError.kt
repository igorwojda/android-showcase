package com.igorwojda.showcase.feature.base.domain.error

sealed interface AppError {
    data object Network : AppError

    data object Unauthorized : AppError

    data object Server : AppError

    data object Unknown : AppError
}
