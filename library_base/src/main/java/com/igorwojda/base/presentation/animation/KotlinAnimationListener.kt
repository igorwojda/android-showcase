package com.igorwojda.base.presentation.animation

import android.view.animation.Animation

class KotlinAnimationListener : Animation.AnimationListener {
    private var onAnimationRepeatListener: ((animation: Animation?) -> Unit)? = null
    private var onAnimationEndListener: ((animation: Animation?) -> Unit)? = null
    private var onAnimationStartListener: ((animation: Animation?) -> Unit)? = null

    fun onAnimationStart(func: (animation: Animation?) -> Unit) {
        onAnimationStartListener = func
    }

    fun onAnimationEnd(func: (animation: Animation?) -> Unit) {
        onAnimationEndListener = func
    }

    fun onAnimationRepeat(func: (animation: Animation?) -> Unit) {
        onAnimationRepeatListener = func
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
