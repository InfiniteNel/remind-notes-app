package jroslar.infinitenel.remindnotes.core.broadcastreceiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.core.Constant
import jroslar.infinitenel.remindnotes.ui.MainActivity

class ReminderNotification: BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_ID = "reminder_id_notification"
        const val NOTIFICATION_TITLE = "reminder_title_notification"
        const val NOTIFICATION_DESCRIPTION = "reminder_description_notification"
    }
    override fun onReceive(context: Context, intent: Intent?) {
        createNotification(context, intent?.extras!!)
    }

    private fun createNotification(context: Context, bundle: Bundle) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, flag)

        val notification = NotificationCompat.Builder(context,
            Constant.CHANNEL_ID_REMINDER_NOTIFICATION
        )
            .setContentTitle(bundle.getString(NOTIFICATION_TITLE))
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(bundle.getString(NOTIFICATION_DESCRIPTION))
            )
            .setSmallIcon(R.drawable.banner_remindnotes)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(bundle.getInt(NOTIFICATION_ID), notification)
    }
}