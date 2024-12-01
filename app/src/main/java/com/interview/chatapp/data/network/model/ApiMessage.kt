package com.interview.chatapp.data.network.model

import com.google.gson.annotations.SerializedName

data class ApiMessage(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String,
    @SerializedName("last_updated") val lastUpdated: String
)