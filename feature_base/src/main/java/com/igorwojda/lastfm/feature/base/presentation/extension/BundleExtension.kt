package com.igorwojda.lastfm.feature.base.presentation.extension

import android.os.Bundle

fun Bundle?.getStringOrThrow(extraKey: String): String {
    val result = this?.getString(extraKey)
    require(!result.isNullOrEmpty()) { "$extraKey is null" }
    return result
}
