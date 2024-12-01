package com.interview.chatapp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.interview.chatapp.data.database.entity.MessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MessagesDao: BaseDao<MessageEntity> {

    @Query("""
    SELECT * FROM messages
    WHERE conversation_id == :conversationId
    ORDER BY 
        CASE WHEN :sortDesc THEN last_updated END DESC,
        CASE WHEN NOT :sortDesc THEN last_updated END ASC
    """)
    fun getMessagesForConversation(conversationId: String, sortDesc: Boolean): Flow<List<MessageEntity>>

    @Query("UPDATE messages SET is_synced = :isSynced WHERE id = :id")
    suspend fun syncMessage(isSynced: Boolean, id: String): Int
}