package com.igorwojda.base.presentation.animation

import android.view.animation.Animation

class KotlinAnimationListener : Animation.AnimationListener {
    private var onAnimationRepeatListener: ((animation: Animation?) -> Unit)? = null
    private var onAnimationEndListener: ((animation: Animation?) -> Unit)? = null
    private var onAnimationStartListener: ((animation: Animation?) -> Unit)? = null

    fun onAnimationStart(listener: (animation: Animation?) -> Unit) {
        onAnimationStartListener = listener
    }

    fun onAnimationEnd(listener: (animation: Animation?) -> Unit) {
        onAnimationEndListener = listener
    }

    fun onAnimationRepeat(listener: (animation: Animation?) -> Unit) {
        onAnimationRepeatListener = listener
    }

    override fun onAnimationEnd(animation: Animation?) {
        onAnimationEndListener?.invoke(animation)
    }

    override fun onAnimationStart(animation: Animation?) {
        onAnimationStartListener?.invoke(animation)
    }

    override fun onAnimationRepeat(animation: Animation?) {
        onAnimationRepeatListener?.invoke(animation)
    }
}

inline fun Animation.setAnimationListener(listener: KotlinAnimationListener.() -> Unit) {
    KotlinAnimationListener().also {
        it.listener()
        setAnimationListener(it)
    }
}
