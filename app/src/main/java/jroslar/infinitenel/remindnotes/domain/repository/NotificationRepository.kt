package jroslar.infinitenel.remindnotes.domain.repository

import jroslar.infinitenel.remindnotes.domain.model.NotificationModel

interface NotificationRepository {
    suspend fun getListNotificationByDay(date: String): List<NotificationModel>
}