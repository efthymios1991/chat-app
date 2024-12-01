package com.interview.chatapp.data.model

data class Message(
    val id: String,
    val text: String,
    val lastUpdated: String,
    val isSynced: Boolean
)