package com.igorwojda.showcase.feature.album.presentation.util

/**
 * provides a String representation of the given time
 * @return `seconds` in mm:ss format
 */
internal fun formatTime(seconds: Int): String {
    return String.format("%02d:%02d", seconds % 3600 / 60, seconds % 60)
}
