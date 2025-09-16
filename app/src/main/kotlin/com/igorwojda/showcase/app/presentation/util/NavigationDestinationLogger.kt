package com.igorwojda.showcase.app.presentation.util

import android.os.Bundle
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

                // Log arguments if they exist
                arguments?.let { bundle ->
                    if (!bundle.isEmpty) {
                        appendLine("   Arguments:")

                        parseBundleStringToLines(arguments).forEach { line ->
                            appendLine(line)
                        }
                    }
                }
            }

        Timber.tag("Navigation").d(logMessage)
    }

    /**
     * Parses a Bundle's string representation and converts it to a formatted list of key-value pairs.
     *
     * Takes the output of Bundle.toString() which has the format:
     * "Bundle[{key1=value1, key2=value2, key3=value3}]"
     *
     * And converts it to a list of formatted strings in the format:
     * "\t\t$key: $value"
     *
     * @param bundleString The string representation of a Bundle (from Bundle.toString())
     * @return List of formatted strings, each representing a key-value pair with tab indentation.
     *         Returns empty list if the bundle string is null, empty, or malformed.
     *
     * @sample
     * ```
     * val bundleStr = "Bundle[{isActive=true, age=30, name=John}]"
     * val result = parseBundleStringToLines(bundleStr)
     * // Result:
     * // ["\t\tisActive: true", "\t\tage: 30", "\t\tname: John"]
     * ```
     */
    private fun parseBundleStringToLines(bundle: Bundle): List<String> {
        val bundleString = bundle.toString()

        // Remove prefix and suffix
        val content =
            bundleString
                .removePrefix("Bundle[{")
                .removeSuffix("}]")
                .trim()

        if (content.isEmpty()) return emptyList()

        // Split by comma and format each pair
        return content
            .split(", ")
            .mapNotNull { pair ->
                val parts = pair.split("=", limit = 2)
                if (parts.size == 2) {
                    val key = parts[0].trim()
                    val value = parts[1].trim()
                    "\t\t$key: $value"
                } else {
                    null
                }
            }
    }
}
