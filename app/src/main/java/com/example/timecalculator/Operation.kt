package com.example.timecalculator

import kotlin.time.Duration

class Operation(private var first: String, private var second: String) {

    fun sum() = fromTextToSec(first) + fromTextToSec(second)

    fun dif() = fromTextToSec(first) - fromTextToSec(second)

    private fun fromTextToSec(value: String): Long{
        if (isValidTimeFormat(value))
            return Duration.parseIsoString("PT0" + value.uppercase()).inWholeSeconds
        else
            throw DataFormatEditTextException("Неверный формат")
    }

    fun operationResult (seconds: Long): String{
        val hours = changeToHours(seconds)
        val minutes = changeToMinutes(seconds)
        var newSeconds = changeToSeconds(seconds)

        return if (hours == 0L) "${minutes}m${newSeconds}s" else "${hours}h${minutes}m${newSeconds}s"
    }

    fun changeToHours(value: Long) = value / 3600
    fun changeToMinutes(value: Long) = value % 3600 / 60
    fun changeToSeconds(value: Long) = value % 60

    fun isValidTimeFormat(time: String): Boolean {
        val parts = time.split("h", "m", "s")

        if (parts.size != 3) {
            return false
        }

        for (part in parts) {
            if (part.toIntOrNull()?.let { it in 0..60 } ?: false) {
                return false
            }
        }
        return true
    }
}