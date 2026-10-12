package com.igorwojda.showcase.feature.base.data.error

import com.igorwojda.showcase.feature.base.domain.error.AppError
import com.igorwojda.showcase.feature.base.domain.result.Result
import io.github.jan.supabase.exceptions.RestException
import java.io.IOException
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException

@Suppress("detekt.MagicNumber")
fun httpError(code: Int): AppError =
    when (code) {
        401, 403 -> AppError.Unauthorized
        in 500..599, 429 -> AppError.Server
        else -> AppError.Unknown
    }

fun Exception.toAppError(): AppError =
    when (this) {
        is CancellationException -> throw this
        is RestException -> httpError(statusCode)
        is HttpException -> httpError(code())
        is IOException -> AppError.Network
        else -> AppError.Unknown
    }

/** Data boundary only; cancellation and JVM Errors propagate. */
suspend fun <T> dataResult(block: suspend () -> Result<T>): Result<T> =
    try {
        block()
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Result.Failure(exception.toAppError())
    }
