package com.interview.chatapp.ui.conversations.viewmodel

import com.interview.chatapp.data.repository.ChatRepository
import com.interview.chatapp.ui.base.BaseViewModel
import com.interview.chatapp.util.vmLaunch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class ConversationsViewModel @Inject constructor(
    private val repository: ChatRepository
): BaseViewModel<ConversationsUiState, ConversationsUiAction>() {

    override val initialState: ConversationsUiState
        get() = ConversationsUiState.Companion.initial()

    init {
        observeConversations()
        fetchData()
    }

    private fun fetchData() {
        vmLaunch(Dispatchers.IO) {
            repository.fetchConversations()
        }
    }

    private fun observeConversations() {
        vmLaunch(Dispatchers.IO) {
            repository.getConversations().collectLatest {
                updateViewState(uiState.value.copy(
                    isLoading = false,
                    isEmpty = it.isEmpty(),
                    conversations = it
                ))
            }
        }
    }
}