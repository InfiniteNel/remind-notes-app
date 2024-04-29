package jroslar.infinitenel.remindnotes.domain.model

import jroslar.infinitenel.remindnotes.core.extensions.dateToMillisecondes
import jroslar.infinitenel.remindnotes.core.extensions.timeToMillisecondes
import jroslar.infinitenel.remindnotes.core.utils.DaysOfWeek
import jroslar.infinitenel.remindnotes.data.database.entities.ReminderEntity

data class ReminderModel(
    val id: Int = 0,
    override val title: String,
    override val description: String,
    val remindDay: String,
    val repeatDay: List<DaysOfWeek>,
    val timeDay: String
): NotificationModel

fun ReminderModel.toEntityReminder(): ReminderEntity = ReminderEntity(id, title, description, remindDay.dateToMillisecondes(), repeatDay, timeDay.timeToMillisecondes())