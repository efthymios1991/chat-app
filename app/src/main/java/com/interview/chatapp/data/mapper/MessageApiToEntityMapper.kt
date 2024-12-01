package com.interview.chatapp.data.mapper

import com.interview.chatapp.data.database.entity.MessageEntity
import com.interview.chatapp.data.network.model.ApiMessage
import com.interview.chatapp.util.DateUtil

class MessageApiToEntityMapper(val conversationId: String): DataMapper<ApiMessage, MessageEntity> {

    override fun map(obj: ApiMessage): MessageEntity {
        return MessageEntity(
            id = obj.id,
            conversationId = conversationId,
            text = obj.text,
            lastUpdated = DateUtil.stringToDate(obj.lastUpdated),
            isSynced = true
        )
    }

    override fun mapReverse(obj: MessageEntity): ApiMessage {
        return ApiMessage(
            id = obj.id,
            text = obj.text,
            lastUpdated = DateUtil.formatDate(obj.lastUpdated)
        )
    }
}