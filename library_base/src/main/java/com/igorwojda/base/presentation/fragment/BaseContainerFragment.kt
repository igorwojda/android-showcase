package com.igorwojda.base.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import com.igorwojda.base.presentation.viewmodel.BaseViewModel
import timber.log.Timber
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty


abstract class BaseContainerFragment : InjectionFragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    protected abstract val viewModel: BaseViewModel

    private val navArgsReflection by navArgsReflection()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutResourceId, null).also {
            Timber.v("onCreateView ${javaClass.simpleName}")
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
//            viewModel.setAgrs(getNavArgsForFragment())
            viewModel.setAgrs(navArgsReflection)
            viewModel.loadData()
        }
    }
}

/**
 * This method will be called during initialization to provide [NavArgs]
 * instance via reflection. If no arguments where passed to fragment this
 * method will return null.
 */

// This code servers as a wrapper for NavArgsLazy class.


class NavArgsReflection<Args : NavArgs?>(
    private val navArgsClass: KClass<out Fragment>,
    private val arguments: () -> Bundle?
) :
    ReadOnlyProperty<Any?, Args?> {

    private var navArgsLazy: Args? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): Args? {

        if (navArgsLazy == null) {
            // SafeArgs plugin adds "Agrs" suffix to the fragment class eg.
            // If com.abc.MyFragment class has arguments defined in nav_graph.xml then
            // class com.abc.MyFragmentArgs will be generated.

            arguments.invoke() ?: return null

            val canonicalName = "${navArgsClass.java.canonicalName}Args"
            @Suppress("UNCHECKED_CAST")
            val navArgsClass = requireNotNull(getClass(canonicalName) as? KClass<NavArgs>) {
                "Arguments where passed to a fragment, but corresponding argument class $canonicalName does not exist. Arguments: $arguments"
            }

            // Let's check if Args class actually exists
            navArgsLazy = NavArgsLazy(navArgsClass) { arguments.invoke() ?: throw  Exception("AAA") }.value as Args
        }

        return navArgsLazy
    }
}

fun getClass(className: String): KClass<*>? = try {
    Class.forName(className).kotlin
} catch (e: ClassNotFoundException) {
    null
}

@MainThread
fun Fragment.navArgsReflection() = NavArgsReflection<NavArgs>(this::class) { arguments }
