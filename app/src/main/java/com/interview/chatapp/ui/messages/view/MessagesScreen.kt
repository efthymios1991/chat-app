package com.interview.chatapp.ui.messages.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.interview.chatapp.ui.messages.viewmodel.MessagesUiAction
import com.interview.chatapp.ui.messages.viewmodel.MessagesViewModel
import com.interview.chatapp.ui.views.TopBar

@Composable
fun MessagesScreen(
    navController: NavController,
    viewModel: MessagesViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = "Messages",
                showBackButton = true
            ) {
                navController.popBackStack()
            }
        },
        bottomBar = {
            ReplyView(
                onSendClick = { replyText ->
                    viewModel.postAction(MessagesUiAction.OnReplySendClicked(replyText))
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.value.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                MessagesList(state.value.messages)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MessagesScreenPreview() {
    MessagesScreen(navController = rememberNavController())
}