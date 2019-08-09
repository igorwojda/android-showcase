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
import kotlin.reflect.KClass


abstract class BaseContainerFragment : InjectionFragment() {

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    protected abstract val viewModel: BaseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layoutResourceId, null).also {
            Timber.v("onCreateView ${javaClass.simpleName}")
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.setAgrs(getNavArgsForFragment())
            viewModel.loadData()
        }
    }
}

/**
 * This method will be called during initialization to provide
 * arguments to construct an [NavArgs] instance via reflection.
 * If no arguments where passed to fragment this method will return null.
 */
@MainThread
fun Fragment.getNavArgsForFragment(): NavArgs? {
    // SafeArgs plugin adds "Agrs" suffix to the fragment class eg.
    // If com.abc.MyFragment class has arguments defined in nav_graph.xml then
    // class com.abc.MyFragmentArgs will be generated.

    val localArguments = arguments ?: return null

    val canonicalName = "${this::class.java.canonicalName}Args"
    @Suppress("UNCHECKED_CAST")
    val navArgsClass = requireNotNull(getClass(canonicalName) as? KClass<NavArgs>) {
        "Arguments where passed to a fragment, but corresponding argument class $canonicalName does not exist. Arguments: $localArguments"
    }

    // Let's check if Args class actually exists
    val navArgs by NavArgsLazy(navArgsClass) { localArguments }
    return navArgs
}

fun getClass(className: String): KClass<*>? = try {
    Class.forName(className).kotlin
} catch (e: ClassNotFoundException) {
    null
}


@MainThread
inline fun <reified Args : NavArgs> Fragment.navArgsByReflection() = NavArgsLazy(Args::class) {
    arguments ?: throw IllegalStateException("Fragment $this has null arguments")
}
