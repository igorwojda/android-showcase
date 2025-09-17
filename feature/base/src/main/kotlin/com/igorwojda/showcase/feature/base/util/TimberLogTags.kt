package com.igorwojda.showcase.feature.base.util

/**
 * Centralized log tags for consistent logging throughout the application.
 *
 * These tags help filter and identify different types of logs during development and debugging:
 * - Use with Timber: `Timber.tag(LogTags.NETWORK).d("message")`
 * - Filter in Logcat by tag to see specific log categories
 */
object TimberLogTags {
    /**
     * Network requests, responses, and HTTP-related logs.
     **/
    const val NETWORK = "Network"

    /**
     * User actions and UI state modifications.
     **/
    const val ACTION = "Action"

    /**
     * Navigation events and route changes.
     **/
    const val NAVIGATION = "Navigation"
}
