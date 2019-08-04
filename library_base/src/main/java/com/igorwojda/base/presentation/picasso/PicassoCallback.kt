package com.igorwojda.base.presentation.picasso

import com.squareup.picasso.Callback

class PicassoCallback : Callback {
    private var onSuccessListener: (() -> Unit)? = null
    private var onErrorListener: (() -> Unit)? = null

    fun onSuccess(listener: () -> Unit) {
        onSuccessListener = listener
    }

    fun onError(listener: () -> Unit) {
        onErrorListener = listener
    }

    override fun onSuccess() {
        onSuccessListener?.invoke()
    }

    override fun onError(e: Exception?) {
        onErrorListener?.invoke()
    }
}
