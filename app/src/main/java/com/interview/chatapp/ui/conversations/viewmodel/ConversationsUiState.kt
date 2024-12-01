package com.interview.chatapp.ui.conversations.viewmodel

import com.interview.chatapp.data.model.Conversation
import com.interview.chatapp.ui.base.UiState

data class ConversationsUiState(
    val isLoading: Boolean,
    val isEmpty: Boolean,
    val conversations: List<Conversation> = emptyList()
): UiState {

    companion object {
        fun initial() = ConversationsUiState(
            isLoading = true,
            isEmpty = false,
        )
    }
}