package com.igorwojda.base.presentation.extension

import androidx.fragment.app.Fragment
import com.igorwojda.base.presentation.fragment.delegate.FragmentArgumentDelegate

inline fun <reified T : Any?> Fragment.argument() = FragmentArgumentDelegate<T>()
