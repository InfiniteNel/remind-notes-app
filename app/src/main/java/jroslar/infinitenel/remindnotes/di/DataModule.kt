package jroslar.infinitenel.remindnotes.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jroslar.infinitenel.remindnotes.data.database.dao.NoteDao
import jroslar.infinitenel.remindnotes.data.repository.NoteRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun provideNoteRepository(noteDao: NoteDao) = NoteRepositoryImpl(noteDao)
}