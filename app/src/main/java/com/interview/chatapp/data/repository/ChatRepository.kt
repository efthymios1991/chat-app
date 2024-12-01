package com.interview.chatapp.data.repository

import com.interview.chatapp.data.model.Conversation
import com.interview.chatapp.data.model.Message
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun fetchConversations()
    fun getConversations(): Flow<List<Conversation>>
    fun getMessages(conversationId: String): Flow<List<Message>>
    suspend fun sendMessage(conversationId: String, text: String): Boolean
}