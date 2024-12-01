package com.interview.chatapp.ui.messages.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.interview.chatapp.data.repository.ChatRepository
import com.interview.chatapp.ui.base.BaseViewModel
import com.interview.chatapp.ui.navigation.MESSAGES_SCREEN_ARGUMENT_ID
import com.interview.chatapp.util.vmLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: ChatRepository
): BaseViewModel<MessagesUiState, MessagesUiAction>() {

    val conversationId = savedStateHandle.get<String>(MESSAGES_SCREEN_ARGUMENT_ID)

    override val initialState: MessagesUiState
        get() = MessagesUiState.initial()

    init {
        conversationId?.let {
            getMessagesForConversation(conversationId)
        }
    }

    override fun getAction(action: MessagesUiAction, previousState: MessagesUiState) {
        when(action) {
            is MessagesUiAction.OnReplySendClicked -> handleSendReply(action.text)
        }
    }

    private fun getMessagesForConversation(id: String) {
        vmLaunch(Dispatchers.IO) {
            repository.getMessages(id).collectLatest {
                updateViewState(uiState.value.copy(
                    isLoading = false,
                    isEmpty = it.isEmpty(),
                    messages = it
                ))
            }
        }
    }

    private fun handleSendReply(text: String) {
        vmLaunch(Dispatchers.IO) {
            repository.sendMessage(conversationId!!, text)
        }
    }
}