package com.igorwojda.base.presentation.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

/**
 * This delegate will be called during initialization to provide [NavArgs]
 * instance via reflection. If no arguments where passed to fragment this
 * method will return null.
 *
 * This class servers as a wrapper for androidx.navigation.NavArgsLazy class.
 */

class NavArgsReflectionLazy<Args : NavArgs?>(
    private val navArgsClass: KClass<out Fragment>,
    private val arguments: () -> Bundle?
) :
    ReadOnlyProperty<Any?, Args?> {

    private var navArgs: NavArgsLazy<*>? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): Args? {
        if (navArgs == null) {
            val localArguments = arguments.invoke() ?: return null

            // SafeArgs plugin adds "Agrs" suffix to the fragment class eg.
            // If com.abc.MyFragment class has arguments defined in nav_graph.xml then
            // class com.abc.MyFragmentArgs will be generated.
            val className = "${navArgsClass.java.canonicalName}Args"

            // Let's check if Args class actually exists
            val navArgsClass = requireNotNull(getArgNavClass(className)) {
                "Arguments where passed to a fragment, but corresponding argument class $className does not exist. Arguments: $arguments"
            }

            navArgs = NavArgsLazy(navArgsClass) { localArguments }
        }

        @Suppress("UNCHECKED_CAST")
        return navArgs?.value as Args
    }

    private fun getArgNavClass(className: String): KClass<NavArgs>? = try {
        @Suppress("UNCHECKED_CAST")
        Class.forName(className).kotlin as KClass<NavArgs>
    } catch (e: ClassNotFoundException) {
        null
    }
}
