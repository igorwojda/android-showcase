package com.igorwojda.showcase.album.presentation.util

object TimeUtil {
    /**
     * provides a String representation of the given time
     * @return `seconds` in mm:ss format
     */
    internal fun formatTime(seconds: Int): String {
        val secondsInMinute = 60
        val secondsInHour = 3600

        @Suppress("detekt.ImplicitDefaultLocale")
        return String.format("%02d:%02d", seconds % secondsInHour / secondsInMinute, seconds % secondsInMinute)
    }
}
