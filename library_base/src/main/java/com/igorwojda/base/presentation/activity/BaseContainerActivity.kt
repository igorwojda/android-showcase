package com.igorwojda.base.presentation.activity

import android.os.Bundle
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import com.igorwojda.library.base.R
import timber.log.Timber

abstract class BaseContainerActivity : BaseActivity() {
    @get:LayoutRes
    override val layoutResId: Int = R.layout.activity_base_container

    private val contentContainerId = R.id.contentContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkContentContainer()

        Timber.v("onCreate ${javaClass.simpleName}")
    }

    private fun checkContentContainer() {
        val contentContainer = findViewById<FrameLayout>(contentContainerId)
        checkNotNull(contentContainer) { "Content container (R.id.contentContainer) does not exist in inflated layout" }
    }

    protected fun replaceScreenContent(fragment: Fragment) {
        supportFragmentManager.transaction { replace(contentContainerId, fragment) }
    }
}
