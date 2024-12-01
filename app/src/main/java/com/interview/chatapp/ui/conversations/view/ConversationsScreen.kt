package com.interview.chatapp.ui.conversations.view


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
import com.interview.chatapp.ui.conversations.viewmodel.ConversationsViewModel
import com.interview.chatapp.ui.views.TopBar

@Composable
fun ConversationsScreen(
    navController: NavController,
    viewModel: ConversationsViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = "Inbox",
                showBackButton = false
            ) { }
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
                ConversationsList(state.value.conversations, navController)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ConversationsScreenPreview() {
    ConversationsScreen(navController = rememberNavController())
}