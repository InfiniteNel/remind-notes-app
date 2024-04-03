package jroslar.infinitenel.remindnotes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jroslar.infinitenel.remindnotes.data.database.dao.NoteDao
import jroslar.infinitenel.remindnotes.data.database.dao.ReminderDao
import jroslar.infinitenel.remindnotes.data.repository.NoteRepositoryImpl
import jroslar.infinitenel.remindnotes.data.repository.NotificationRepositoryImpl
import jroslar.infinitenel.remindnotes.data.repository.ReminderRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideNoteRepository(noteDao: NoteDao) = NoteRepositoryImpl(noteDao)

    @Provides
    fun provideReminderRepository(reminderDao: ReminderDao) = ReminderRepositoryImpl(reminderDao)

    @Provides
    fun provideNotificationRepository(noteDao: NoteDao, reminderDao: ReminderDao) = NotificationRepositoryImpl(noteDao, reminderDao)
}