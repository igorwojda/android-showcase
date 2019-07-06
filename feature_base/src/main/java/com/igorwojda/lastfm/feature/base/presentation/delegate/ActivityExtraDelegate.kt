package com.igorwojda.lastfm.feature.base.presentation.delegate

import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ActivityExtraDelegate<T : Any?> : ReadOnlyProperty<AppCompatActivity, T> {

    private var value: T? = null

    override operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (value == null) {
            val extras = requireNotNull(thisRef.intent.extras) {
                "Cannot read property ${property.name}. No intent extra have been set"
            }

            @Suppress("UNCHECKED_CAST")
            value = extras.get(property.name) as T?
        }

        @Suppress("UNCHECKED_CAST")
        return value as T
    }
}
