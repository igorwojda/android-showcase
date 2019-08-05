package com.igorwojda.base.presentation.extension

import androidx.appcompat.app.AppCompatActivity
import com.igorwojda.base.presentation.activity.delegate.ActivityExtraDelegate

inline fun <reified T> AppCompatActivity.extra() = ActivityExtraDelegate<T>()

