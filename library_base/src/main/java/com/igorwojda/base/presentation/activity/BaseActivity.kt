package com.igorwojda.base.presentation.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import com.igorwojda.base.presentation.activity.delegate.ActivityExtraDelegate
import timber.log.Timber

abstract class BaseActivity : InjectionActivity() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState).also {
        setContentView(layoutResourceId)

        supportActionBar?.hide()

        // The window will not be resized when virtual keyboard is shown (eg. bottom navigation bar will be hidden under virtual keyboard)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)

        Timber.v("onCreate ${javaClass.simpleName}")
    }

    protected inline fun <reified T : Any?> extra() =
        ActivityExtraDelegate<T>()
}
