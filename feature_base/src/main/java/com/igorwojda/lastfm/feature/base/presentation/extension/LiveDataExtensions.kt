package com.igorwojda.lastfm.feature.base.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNullable(owner: LifecycleOwner, f: (T?) -> Unit) {
    this.observe(owner, Observer<T> { t -> f(t) })
}

fun <T> LiveData<T>.observeNotNull(owner: LifecycleOwner, f: (T) -> Unit) {
    this.observe(owner, Observer<T> { t -> t?.let(f) })
}
