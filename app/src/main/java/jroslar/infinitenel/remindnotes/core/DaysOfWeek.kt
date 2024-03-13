package jroslar.infinitenel.remindnotes.core

import android.content.Context
import jroslar.infinitenel.remindnotes.R

enum class DaysOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    fun getValue(context: Context): String {
        return when (this) {
            MONDAY -> context.getString(R.string.monday)
            TUESDAY -> context.getString(R.string.tuesday)
            WEDNESDAY -> context.getString(R.string.wednesday)
            THURSDAY -> context.getString(R.string.thursday)
            FRIDAY -> context.getString(R.string.friday)
            SATURDAY -> context.getString(R.string.saturday)
            SUNDAY -> context.getString(R.string.sunday)
        }
    }
}