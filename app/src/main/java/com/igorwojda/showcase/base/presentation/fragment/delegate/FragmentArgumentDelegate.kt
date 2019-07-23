package com.igorwojda.showcase.base.presentation.fragment.delegate

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.igorwojda.showcase.base.presentation.extension.putAny
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*
Delegate should be the only client modifying fragment arguments (fragment arguments should not be modified directly
inside fragment instance due to potential type mismatch that may result in crash)
 */
class FragmentArgumentDelegate<T : Any?> : ReadWriteProperty<Fragment, T> {

    private var value: T? = null

    override operator fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (value == null) {
            val arguments = requireNotNull(thisRef.arguments) {
                "Cannot read argument ${property.name}. No arguments have been set"
            }

            @Suppress("UNCHECKED_CAST")
            value = arguments.get(property.name) as T?
        }

        @Suppress("UNCHECKED_CAST")
        return value as T
    }

    override operator fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        (thisRef.arguments ?: Bundle())
            .apply { putAny(property.name, value) }
            .also { thisRef.arguments = it }
    }
}
