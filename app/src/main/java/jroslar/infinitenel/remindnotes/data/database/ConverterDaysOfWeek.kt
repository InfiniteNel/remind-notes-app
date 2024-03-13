package jroslar.infinitenel.remindnotes.data.database

import androidx.room.TypeConverter
import jroslar.infinitenel.remindnotes.core.DaysOfWeek

class ConverterDaysOfWeek {
    @TypeConverter
    fun fromList(days: List<DaysOfWeek>): String {
        return days.joinToString(",")
    }

    @TypeConverter
    fun toList(daysString: String): List<DaysOfWeek> {
        return if
                (daysString.isEmpty()) emptyList()
        else
            daysString.split(",").map { DaysOfWeek.valueOf(it) }
    }
}