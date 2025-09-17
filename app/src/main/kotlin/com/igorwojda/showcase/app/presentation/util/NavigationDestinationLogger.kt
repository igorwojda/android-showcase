package com.igorwojda.showcase.app.presentation.util

import android.os.Bundle
import androidx.navigation.NavDestination
import com.igorwojda.showcase.app.presentation.NavigationRoute
import com.igorwojda.showcase.feature.base.util.TimberLogTags
import timber.log.Timber

object NavigationDestinationLogger {
    fun logDestinationChange(
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        val className = NavigationRoute::class.simpleName
        val destinationRoute = destination.route?.substringAfter("$className.") ?: "Unknown"
        val destinationId = destination.id
        val destinationLabel = destination.label ?: "No Label"

        val logMessage =
            buildString {
                appendLine("Navigation destination changed:")
                appendLine("\tRoute: $destinationRoute")
                appendLine("\tID: $destinationId")
                appendLine("\tLabel: $destinationLabel")

                arguments?.let { bundle ->
                    if (!bundle.isEmpty) {
                        appendLine("   Arguments:")
                        bundle.keySet().forEach { key ->
                            val value = getValueFromBundle(bundle, key) ?: "null"
                            appendLine("\t\t$key: $value")
                        }
                    }
                }
            }

        Timber.tag(TimberLogTags.NAVIGATION).d(logMessage)
    }

    /**
     * Retrieves a value from Bundle using Android Navigation supported types.
     * Navigation supports: String, Int, Long, Float, Boolean, Parcelable, Serializable, and their arrays.
     *
     * @return String representation of the value, or null if no matching type found
     */
    private fun getValueFromBundle(
        bundle: Bundle,
        key: String,
    ): String? =
        bundle.getString(key)?.let { "\"$it\"" }
            ?: runCatching { bundle.getInt(key) }.getOrNull()?.toString()
            ?: runCatching { bundle.getLong(key) }.getOrNull()?.toString()
            ?: runCatching { bundle.getFloat(key) }.getOrNull()?.toString()
            ?: runCatching { bundle.getBoolean(key) }.getOrNull()?.toString()
            ?: bundle.getStringArray(key)?.contentToString()
            ?: bundle.getIntArray(key)?.contentToString()
            ?: bundle.getLongArray(key)?.contentToString()
            ?: bundle.getFloatArray(key)?.contentToString()
            ?: bundle.getBooleanArray(key)?.contentToString()
}
