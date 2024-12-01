package com.interview.chatapp.ui.messages.viewmodel

import com.interview.chatapp.ui.base.UiAction

sealed class MessagesUiAction: UiAction {
    data class OnReplySendClicked(val text: String): MessagesUiAction()
}