package jroslar.infinitenel.remindnotes.core.extensions

import jroslar.infinitenel.remindnotes.core.Constant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.millisecondesToDateString(): String {
    val format = SimpleDateFormat(Constant.FORMAT_DATE_OVER_API_26, Locale.getDefault())
    return if (this == Constant.EMPTY_DATE_MILLISECONDES) {
        ""
    } else {
        val dateMillisecondes = Date(this)
        format.format(dateMillisecondes)
    }
}

fun Long.millisecondesToTimeString(): String {
    val hours = this / 3600000
    val minutes = (this % 3600000) / 60000
    return "%02d:%02d".format(hours, minutes)
}