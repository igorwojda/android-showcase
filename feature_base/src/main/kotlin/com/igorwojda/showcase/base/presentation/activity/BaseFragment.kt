package com.igorwojda.showcase.base.presentation.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import timber.log.Timber

open class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("onCreate ${javaClass.simpleName}")
    }
}
