package jroslar.infinitenel.remindnotes.core.broadcastreceiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import dagger.hilt.android.AndroidEntryPoint
import jroslar.infinitenel.remindnotes.R
import jroslar.infinitenel.remindnotes.core.Constant
import jroslar.infinitenel.remindnotes.core.utils.DateUtils
import jroslar.infinitenel.remindnotes.core.utils.DaysOfWeek
import jroslar.infinitenel.remindnotes.core.utils.NotificationUtils
import jroslar.infinitenel.remindnotes.domain.model.ReminderModel
import jroslar.infinitenel.remindnotes.domain.usecase.GetReminderById
import jroslar.infinitenel.remindnotes.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class ReminderNotification : BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_ID = "reminder_id_notification"
    }

    @Inject
    lateinit var getReminderById: GetReminderById

    override fun onReceive(context: Context, intent: Intent?) {
        val bundle = intent?.extras!!
        val notificationId = bundle.getInt(NOTIFICATION_ID)

        CoroutineScope(Dispatchers.IO).launch {
            val dataNotification = getReminderById(notificationId)
            createNotification(context, dataNotification)

            if (dataNotification.remindDay.isEmpty() &&
                dataNotification.repeatDay.isNotEmpty() &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNewReminder(context, dataNotification)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNewReminder(context: Context, reminder: ReminderModel) {
        val currentDayOfWeek = LocalDate.now().dayOfWeek.toString()
        val repeatDay = reminder.repeatDay.indexOf(DaysOfWeek.valueOf(currentDayOfWeek))

        val nextDate = DateUtils.getNextDateOfDayOfWeek(
            reminder.repeatDay[(repeatDay + 1) % reminder.repeatDay.size]
        )

        NotificationUtils.createScheduledNotification(context, nextDate, reminder.id)
    }

    private fun createNotification(context: Context, reminder: ReminderModel) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, flag)

        val notification = NotificationCompat.Builder(context,
            Constant.CHANNEL_ID_REMINDER_NOTIFICATION
        )
            .setContentTitle(reminder.title)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(reminder.description)
            )
            .setSmallIcon(R.drawable.banner_remindnotes)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(reminder.id, notification)
    }
}