package com.geek.android_trainee_task_2021.extension

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

@SuppressLint("SimpleDateFormat")
internal fun Long.toDate(strFormat: String): String {
    val date = Date(TimeUnit.SECONDS.toMillis(this))
    val format = SimpleDateFormat(strFormat)
    return format.format(date)
}

internal fun toDaytime(sunrise: Long?, sunset: Long?): String {

    if (sunrise != null && sunset != null) {

        var diff =
            Date(TimeUnit.SECONDS.toMillis(sunrise)).time - Date(TimeUnit.SECONDS.toMillis(sunset)).time

        diff = abs(diff)
        val diffMinutes: Long = diff / (60 * 1000) % 60
        val diffHours: Long = diff / (60 * 60 * 1000) % 24

        return "${diffHours}h${diffMinutes}m"
    }
    return ""
}

@SuppressLint("SimpleDateFormat")
internal fun Long.toTime(timeZone: String): String {

    val date = Date(TimeUnit.SECONDS.toMillis(this))
    val format = SimpleDateFormat("HH:mm")
    format.timeZone = TimeZone.getTimeZone(timeZone)

    return format.format(date)
}

fun isDay(sunset: Long?, sunrise: Long?): Boolean {

    val current = Date(System.currentTimeMillis() * 1000)
    val before = sunrise?.times(1000)?.let { Date(it) }
    val after = sunset?.times(1000)?.let { Date(it) }

    return when {
        current > before -> false
        current < after -> true
        else -> true
    }
}