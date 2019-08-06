package com.igorwojda.base.presentation.animation

import android.view.View
import android.view.animation.AlphaAnimation
import kotlin.properties.Delegates

class AlphaAnimationHelper internal constructor(
    private val view: View,
    shown: Boolean = false,
    private val animationDuration: Long = 200
) {

    private val fadeInAnimation by lazy {
        AlphaAnimation(0f, 1f).apply {
            fillAfter = true
        }
    }

    private val fadeOutAnimation by lazy {
        AlphaAnimation(1f, 0f).apply {
            fillAfter = true
            setAnimationListener {
                onAnimationEnd {
                    view.clearAnimation()
                }
            }
        }
    }

    private var shown by Delegates.observable(shown) { _, old, new ->
        if (old == new) return@observable

        fadeInAnimation.duration = animationDuration
        fadeOutAnimation.duration = animationDuration

        val animation = if (new) fadeInAnimation else fadeOutAnimation
        view.startAnimation(animation)
    }

    fun show() {
        shown = true
    }

    fun hide() {
        shown = false
    }

    fun dispose() {
        view.clearAnimation()
    }
}

class AlphaAnimationHelperFactory {

    fun create(
        view: View,
        shown: Boolean,
        animationDurationValue: Long = 300
    ) = AlphaAnimationHelper(view, shown, animationDurationValue)
}
