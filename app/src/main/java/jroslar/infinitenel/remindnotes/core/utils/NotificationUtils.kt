package jroslar.infinitenel.remindnotes.core.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import jroslar.infinitenel.remindnotes.core.broadcastreceiver.ReminderNotification

object NotificationUtils {
    @SuppressLint("ScheduleExactAlarm")
    fun createScheduledNotification(
        context: Context, date: Long, id: Int, title: String, description: String
    ) {
        val intent = Intent(context, ReminderNotification::class.java)
        intent.putExtra(ReminderNotification.NOTIFICATION_ID, id)
        intent.putExtra(ReminderNotification.NOTIFICATION_TITLE, title)
        intent.putExtra(ReminderNotification.NOTIFICATION_DESCRIPTION, description)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            id,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, date, pendingIntent)
    }
}