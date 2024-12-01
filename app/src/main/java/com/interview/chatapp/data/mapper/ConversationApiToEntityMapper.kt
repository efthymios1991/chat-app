package com.interview.chatapp.data.mapper

import com.interview.chatapp.data.database.entity.ConversationEntity
import com.interview.chatapp.data.network.model.ApiConversation
import com.interview.chatapp.util.DateUtil

object ConversationApiToEntityMapper: DataMapper<ApiConversation, ConversationEntity> {

    override fun map(obj: ApiConversation): ConversationEntity {
        return ConversationEntity(
            id = obj.id,
            name = obj.name,
            lastUpdated = DateUtil.stringToDate(obj.lastUpdated)
        )
    }

    override fun mapReverse(obj: ConversationEntity): ApiConversation {
        return ApiConversation(
            id = obj.id,
            name = obj.name,
            lastUpdated = DateUtil.formatDate(obj.lastUpdated)
        )
    }
}