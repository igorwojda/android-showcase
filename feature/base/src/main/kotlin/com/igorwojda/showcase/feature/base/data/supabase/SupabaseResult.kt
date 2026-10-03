package com.igorwojda.showcase.feature.base.data.supabase

import com.igorwojda.showcase.feature.base.data.error.dataResult
import com.igorwojda.showcase.feature.base.domain.result.Result

/** Wrap query and decoding inside the Data boundary. */
suspend fun <T> supabaseResult(block: suspend () -> T): Result<T> = dataResult { Result.Success(block()) }
