package com.interview.chatapp.ui.messages.viewmodel

import com.interview.chatapp.data.model.Message
import com.interview.chatapp.ui.base.UiState

data class MessagesUiState(
    val isLoading: Boolean,
    val isEmpty: Boolean,
    val messages: List<Message> = emptyList()
): UiState {

    companion object {
        fun initial() = MessagesUiState(
            isLoading = true,
            isEmpty = false
        )
    }
}