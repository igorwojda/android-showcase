package com.igorwojda.showcase.app.feature

import com.igorwojda.showcase.BuildConfig

// Dynamic Feature modules require reversed dependency (dynamic feature module depends on app module)
// This means we have to use reflection to access module content
// See: https://medium.com/mindorks/dynamic-feature-modules-the-future-4bee124c0f1
@Suppress("detekt.UnsafeCast")
object FeatureManager {

    private const val featurePackagePrefix = "com.igorwojda.showcase.feature"
    private val featureNames = BuildConfig.FEATURE_MODULE_NAMES

    val kodeinModules = featureNames
        .map { "$featurePackagePrefix.$it.FeatureKodeinModule" }
        .map {
            try {
                Class.forName(it).kotlin.objectInstance
            } catch (e: ClassNotFoundException) {
                throw ClassNotFoundException("Kodein module class not found $it")
            }
        }
        .map { it as KodeinModuleProvider }
        .map { it.kodeinModule }
}
