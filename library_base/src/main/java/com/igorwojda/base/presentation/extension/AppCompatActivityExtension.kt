package com.igorwojda.base.presentation.extension

import androidx.appcompat.app.AppCompatActivity

inline fun <reified T> AppCompatActivity.extra(key: String): Lazy<T> = lazy {
    val value = intent.extras?.get(key)
    if (value is T) {
        value
    } else {
        throw IllegalArgumentException("Extra \"$key\" not found")
    }
}
