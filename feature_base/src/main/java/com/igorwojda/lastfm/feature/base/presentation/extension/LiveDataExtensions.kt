package com.igorwojda.lastfm.feature.base.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any?, L : LiveData<T>> LifecycleOwner.observeNullable(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer<T> { t -> t?.let(body) })
}

fun <T : Any, L : LiveData<T>> LifecycleOwner.observeNotNull(liveData: L, body: (T) -> Unit) {
    liveData.observe(this, Observer<T> { t -> t?.let(body) })
}
