package com.interview.chatapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.interview.chatapp.data.database.dao.ConversationsDao
import com.interview.chatapp.data.database.dao.MessagesDao
import com.interview.chatapp.data.database.entity.ConversationEntity
import com.interview.chatapp.data.database.entity.Converters
import com.interview.chatapp.data.database.entity.MessageEntity

@Database(
    entities = [ConversationEntity::class, MessageEntity::class],
    version = DatabaseConstants.DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun chatsDao(): ConversationsDao

    abstract fun messagesDao(): MessagesDao
}