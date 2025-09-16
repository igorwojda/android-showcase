package com.igorwojda.showcase.app.presentation.util

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDestination
import com.igorwojda.showcase.app.presentation.NavigationRoute
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

        Timber.tag("Navigation").d(logMessage)
    }

    /**
     * Retrieves a value from Bundle using Android Navigation supported types.
     * Navigation supports: String, Int, Long, Float, Boolean, Parcelable, Serializable, and their arrays.
     *
     * @return String representation of the value, or null if no matching type found
     */
    private fun getValueFromBundle(bundle: Bundle, key: String): String? {
        // Basic types supported by Navigation
        bundle.getString(key)?.let { return "\"$it\"" }
        bundle.getInt(key, Int.MIN_VALUE).takeIf { it != Int.MIN_VALUE }?.let { return it.toString() }
        bundle.getLong(key, Long.MIN_VALUE).takeIf { it != Long.MIN_VALUE }?.let { return it.toString() }
        bundle.getFloat(key, Float.NaN).takeIf { !it.isNaN() }?.let { return it.toString() }
        bundle.getBoolean(key, false).let {
            if (bundle.containsKey(key)) return it.toString()
        }

        // Array types supported by Navigation
        bundle.getStringArray(key)?.let { return it.contentToString() }
        bundle.getIntArray(key)?.let { return it.contentToString() }
        bundle.getLongArray(key)?.let { return it.contentToString() }
        bundle.getFloatArray(key)?.let { return it.contentToString() }
        bundle.getBooleanArray(key)?.let { return it.contentToString() }

        // Complex types supported by Navigation
        bundle.getParcelable<Parcelable>(key)?.let { return it.toString() }
        bundle.getParcelableArray(key)?.let { return it.contentToString() }
        bundle.getSerializable(key)?.let { return it.toString() }

        return null
    }
}
