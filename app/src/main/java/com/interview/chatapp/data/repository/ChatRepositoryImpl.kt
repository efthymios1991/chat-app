package com.interview.chatapp.data.repository

import android.content.Context
import com.google.gson.reflect.TypeToken
import com.interview.chatapp.data.database.dao.ConversationsDao
import com.interview.chatapp.data.database.dao.MessagesDao
import com.interview.chatapp.data.database.entity.ConversationEntity
import com.interview.chatapp.data.database.entity.MessageEntity
import com.interview.chatapp.data.mapper.ConversationApiToEntityMapper
import com.interview.chatapp.data.mapper.MessageApiToEntityMapper
import com.interview.chatapp.data.model.Conversation
import com.interview.chatapp.data.model.Message
import com.interview.chatapp.data.network.model.ApiConversation
import com.interview.chatapp.util.DataUtil
import com.interview.chatapp.util.DateUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val context: Context,
    private val conversationsDao: ConversationsDao,
    private val messagesDao: MessagesDao
): ChatRepository {

    override suspend fun fetchConversations(): Unit = withContext(Dispatchers.IO) {
        // in a real case app this function should fetch paged data or updates
        // if we pass the last synced timestamp, parse the received data and update
        // the local cache, room db in our case. For the this demo app, we will add a
        // 2 sec delay that represents the network delay and get teh data from the given
        // json that is locally stored in assets folder.
        delay(2000)
        val jsonString = DataUtil.loadJSONFromAssets(context, "conversations-data.json")
        val inboxes = DataUtil.parseJSON<ApiConversation>(jsonString, object : TypeToken<List<ApiConversation>>() {}.type)
        val (inboxEntities, messageEntities) = mapToEntities(inboxes)

        conversationsDao.insertList(inboxEntities)
        messagesDao.insertList(messageEntities)
    }

    fun mapToEntities(inboxes: List<ApiConversation>): Pair<List<ConversationEntity>, List<MessageEntity>> {
        val inboxEntities = mutableListOf<ConversationEntity>()
        val messageEntities = mutableListOf<MessageEntity>()

        inboxes.forEach { inbox ->
            inboxEntities.add(ConversationApiToEntityMapper.map(inbox))
            inbox.messages?.let {
                messageEntities.addAll(MessageApiToEntityMapper(inbox.id).mapList(inbox.messages))
            }
        }
        return Pair(inboxEntities, messageEntities)
    }

    override fun getConversations(): Flow<List<Conversation>> {
        return conversationsDao.getConversations(sortDesc = true)
            .map { entities ->
                entities.map {
                    Conversation(
                        id = it.id,
                        name = it.name,
                        lastUpdated = DateUtil.formatDate(it.lastUpdated, "dd MMM hh:mm")
                    )
                }
            }
    }

    override fun getMessages(conversationId: String): Flow<List<Message>> {
        return messagesDao.getMessagesForConversation(
            conversationId = conversationId,
            sortDesc = true
        ).map { entities ->
            entities.map { entity ->
                Message(
                    id = entity.id,
                    text = entity.text,
                    lastUpdated = DateUtil.formatDate(entity.lastUpdated, "dd MMM hh:mm"),
                    isSynced = entity.isSynced
                )
            }
        }
    }

    override suspend fun sendMessage(conversationId: String, text: String): Boolean =
        withContext(Dispatchers.IO){
            val uuid = UUID.randomUUID().toString()
            val success = messagesDao.insert(MessageEntity(
                id = uuid,
                conversationId = conversationId,
                text = text,
                lastUpdated = Date()
            )) > 0

            if (success) {
                // sync with the server by sending this message
                // Upon success, update the local storage to reflect the message is synced
                // for the demo purposes, we will always sync after a small delay of 2 sec
                delay(2000)
                messagesDao.syncMessage(isSynced = true, id = uuid)
            }

            return@withContext success
        }
}