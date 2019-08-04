package com.igorwojda.base.presentation.animation

import android.view.View
import android.view.animation.AlphaAnimation
import kotlin.properties.Delegates

class AlphaAnimationHelper internal constructor(
    private val view: View,
    shown: Boolean = false,
    private val animationDuration: Long = 200
) {

    companion object {
        const val FADE_OUT_VISIBILITY = View.INVISIBLE
    }

    private val fadeInAnimation by lazy {
        AlphaAnimation(0f, 1f).apply {
            fillAfter = true
            setAnimationListener {
                onAnimationStart {
                    view.visibility = View.VISIBLE
                }
            }
        }
    }

    private val fadeOutAnimation by lazy {
        AlphaAnimation(1f, 0f).apply {
            fillAfter = true
            setAnimationListener {
                onAnimationEnd {
                    view.visibility = FADE_OUT_VISIBILITY
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

    init {
        view.visibility = if (shown) View.VISIBLE else FADE_OUT_VISIBILITY
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
