package jroslar.infinitenel.remindnotes.core.extensions

import jroslar.infinitenel.remindnotes.core.Constant
import java.text.SimpleDateFormat
import java.util.Locale

fun String.dateToMillisecondes(): Long {
    val format = SimpleDateFormat(Constant.FORMAT_DATE_OVER_API_26, Locale.getDefault())
    return if (this.isNotEmpty()) {
        val dateString = format.parse(this)
        dateString?.time ?: Constant.EMPTY_DATE_MILLISECONDES
    } else {
        Constant.EMPTY_DATE_MILLISECONDES
    }
}

fun String.timeToMillisecondes(): Long {
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    val timeString = format.parse(this)
    return timeString?.time ?: 0L
}