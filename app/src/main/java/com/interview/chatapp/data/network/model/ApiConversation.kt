package com.interview.chatapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiConversation(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("messages") val messages: List<ApiMessage>? = emptyList(),
)