package jroslar.infinitenel.remindnotes.ui.reminders

import jroslar.infinitenel.remindnotes.domain.model.ReminderModel

sealed class RemindersState {
    data class NoData(val remindersList: List<ReminderModel>): RemindersState()
    data class Success(val remindersList: List<ReminderModel>): RemindersState()
}