package com.igorwojda.showcase.app.kodein

import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import org.kodein.di.Kodein
import org.kodein.di.bindings.BindingKodein
import org.kodein.di.bindings.ExternalSource
import org.kodein.di.fullErasedName
import org.kodein.di.jvmType
import kotlin.reflect.KClass

class FragmentArgsExternalSource : ExternalSource {
    override fun getFactory(kodein: BindingKodein<*>, key: Kodein.Key<*, *, *>): ((Any?) -> Any)? {
        val fragment = kodein.context as? Fragment

        if (fragment != null) {
            val deductedArgsClassName = fragment.javaClass.canonicalName + "Args"

            if (deductedArgsClassName == key.type.jvmType.fullErasedName()) {

                val navArgsInstance = getNavArgsInstance(fragment)

                if (navArgsInstance != null) {
                    return { navArgsInstance }
                }
            }
        }

        return null
    }

    // This function provides appropriate [NavArgs] instance using reflection to derive
    // Args class name from fragment class name.
    @MainThread
    private fun getNavArgsInstance(fragment: Fragment): NavArgs? {
        val arguments = fragment.arguments ?: return null

        // SafeArgs plugin adds "Agrs" suffix to the fragment class eg.
        // If com.abc.MyFragment class has arguments defined in nav_graph.xml then
        // class com.abc.MyFragmentArgs will be generated.
        val safeArgsClassSuffix = "Args"
        val className = "${fragment::class.java.canonicalName}$safeArgsClassSuffix"

        @Suppress("UNCHECKED_CAST")
        val navArgsClass = requireNotNull(getArgNavClass(className)) {
            // This may happen when nav graph resource does not define arguments for particular fragment
            "Fragment $className has arguments, but corresponding navArgs class $className does not exist."
        }

        // Let's check if Args class actually exists
        val navArgs by NavArgsLazy(navArgsClass) { arguments }
        return navArgs
    }

    private fun getArgNavClass(className: String): KClass<NavArgs>? = try {
        @Suppress("UNCHECKED_CAST")
        Class.forName(className).kotlin as KClass<NavArgs>
    } catch (e: ClassNotFoundException) {
        null
    }
}
