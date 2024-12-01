package com.interview.chatapp.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.interview.chatapp.data.database.entity.ConversationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ConversationsDao: BaseDao<ConversationEntity> {

    @Query("""
    SELECT * FROM conversations
    ORDER BY 
        CASE WHEN :sortDesc THEN last_updated END DESC,
        CASE WHEN NOT :sortDesc THEN last_updated END ASC
    """)
    fun getConversations(sortDesc: Boolean): Flow<List<ConversationEntity>>

}