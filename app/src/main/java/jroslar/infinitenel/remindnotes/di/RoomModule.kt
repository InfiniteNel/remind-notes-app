package jroslar.infinitenel.remindnotes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jroslar.infinitenel.remindnotes.data.database.NotificationDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "notification_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NotificationDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideNoteDao(db: NotificationDatabase) = db.getNoteDao()
}