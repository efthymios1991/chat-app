package com.interview.chatapp.di

import android.content.Context
import androidx.room.Room
import com.interview.chatapp.data.database.AppDatabase
import com.interview.chatapp.data.database.DatabaseConstants
import com.interview.chatapp.data.database.dao.ConversationsDao
import com.interview.chatapp.data.database.dao.MessagesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DatabaseConstants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideChatsDao(db: AppDatabase): ConversationsDao = db.chatsDao()

    @Provides
    fun provideMessagesDao(db: AppDatabase): MessagesDao = db.messagesDao()
}