package me.tyfcho.rideControl.util

/**
 * Utility object for command-related functions.
 */
object CommandUtils {
    /**
     * Checks if the given status is valid.
     *
     * @param status The status to check.
     * @return True if the status is valid, false otherwise.
     */
    fun isValidStatus(status: String): Boolean {
        return status in listOf("open", "closed", "maintenance")
    }
}