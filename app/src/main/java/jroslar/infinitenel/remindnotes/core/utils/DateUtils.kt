package jroslar.infinitenel.remindnotes.core.utils

import android.os.Build
import jroslar.infinitenel.remindnotes.core.Constant
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateUtils {
    fun intsToDateFormat(year: Int, month: Int, dayOfMonth: Int): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localDate = LocalDate.of(year, month + 1, dayOfMonth)
            val formatter = DateTimeFormatter.ofPattern(Constant.FORMAT_DATE_OVER_API_26, Locale.getDefault())
            localDate.format(formatter)
        } else {
            val localdate = String.format("%02d-%02d-%04d", month + 1, dayOfMonth, year)
            val inputDateFormat = SimpleDateFormat(Constant.FORMAT_DATE_UNDER_API_26, Locale.getDefault())
            val inputDate = inputDateFormat.parse(localdate)
            val formatter = SimpleDateFormat(Constant.FORMAT_DATE_OVER_API_26, Locale.getDefault())
            formatter.format(inputDate!!)
        }
    }
}