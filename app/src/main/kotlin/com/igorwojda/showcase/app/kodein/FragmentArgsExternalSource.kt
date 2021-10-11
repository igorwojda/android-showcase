package com.igorwojda.showcase.app.kodein

import android.os.Bundle
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

@Suppress("detekt.ReturnCount")
class FragmentArgsExternalSource : ExternalSource {
    override fun getFactory(kodein: BindingKodein<*>, key: Kodein.Key<*, *, *>): ((Any?) -> Any)? {

        val fragment = kodein.context as? Fragment ?: return null
        val arguments = fragment.arguments ?: return null
        val fragmentClassCanonicalName = fragment.javaClass.canonicalName ?: return null

        // SafeArgs plugin adds "Agrs" suffix to the fragment class eg.
        // If com.abc.MyFragment class has arguments defined in nav_graph.xml then
        // class com.abc.MyFragmentArgs will be generated.
        val argsClassName = fragmentClassCanonicalName + "Args"

        if (argsClassName == key.type.jvmType.fullErasedName()) {

            val navArgsInstance = getNavArgsInstance(argsClassName, arguments)

            if (navArgsInstance != null) {
                return { navArgsInstance }
            }
        }

        return null
    }

    // This function provides appropriate [NavArgs] instance using reflection to derive
    // Args class name from fragment class name.
    @MainThread
    private fun getNavArgsInstance(argsClassName: String, arguments: Bundle): NavArgs? {

        @Suppress("UNCHECKED_CAST")
        val navArgsClass = requireNotNull(getArgNavClass(argsClassName)) {
            // This may happen when nav graph resource does not define arguments for particular fragment
            "Fragment $argsClassName has arguments, but corresponding navArgs class $argsClassName does not exist."
        }

        // Let's check if Args class actually exists
        val navArgs by NavArgsLazy(navArgsClass) { arguments }
        return navArgs
    }

    @Suppress("detekt.SwallowedException")
    private fun getArgNavClass(className: String): KClass<NavArgs>? = try {
        @Suppress("UNCHECKED_CAST")
        Class.forName(className).kotlin as KClass<NavArgs>
    } catch (e: ClassNotFoundException) {
        null
    }
}
