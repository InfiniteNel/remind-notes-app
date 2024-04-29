package jroslar.infinitenel.remindnotes.domain.usecase

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import jroslar.infinitenel.remindnotes.core.utils.NotificationUtils
import jroslar.infinitenel.remindnotes.data.repository.ReminderRepositoryImpl
import javax.inject.Inject

class DeleteReminderUseCase @Inject constructor(
    private val repositoryImpl: ReminderRepositoryImpl,
    @ApplicationContext private val context: Context
) {
    suspend operator fun invoke(id: Int) {
        repositoryImpl.deleteReminder(id)
        NotificationUtils.cancelScheduledNotification(context, id)
    }
}