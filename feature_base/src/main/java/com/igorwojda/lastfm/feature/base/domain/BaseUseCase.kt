package com.igorwojda.lastfm.feature.base.domain

abstract class BaseUseCase<RESULT> {
    abstract suspend fun execute(): RESULT
}
