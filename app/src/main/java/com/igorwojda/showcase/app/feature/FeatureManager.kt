package com.igorwojda.showcase.app.feature

import com.igorwojda.showcase.BuildConfig

// Dynamic Feature modules require reversed dependency (dynamic feature module depends on app module)
// This means we have to use reflection to access module content
// See: https://medium.com/mindorks/dynamic-feature-modules-the-future-4bee124c0f1
@Suppress("detekt.UnsafeCast")
object FeatureManager {

    private const val featurePackagePrefix = "com.igorwojda.showcase.feature"

    @Suppress("detekt.SwallowedException")
    val koinModules = BuildConfig.FEATURE_MODULE_NAMES
        .map { "$featurePackagePrefix.$it.FeatureKoinModuleProvider" }
        .map {
            try {
                Class.forName(it).kotlin.objectInstance as KoinModuleProvider
            } catch (e: ClassNotFoundException) {
                throw ClassNotFoundException("Koin module class not found $it")
            }
        }
        .map { it.modules }
        .flatten()
}
