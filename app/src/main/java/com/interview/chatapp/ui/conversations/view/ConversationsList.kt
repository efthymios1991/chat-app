package com.interview.chatapp.ui.conversations.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.interview.chatapp.data.model.Conversation
import com.interview.chatapp.ui.navigation.Screen

@Composable
fun ConversationsList(conversations: List<Conversation>, navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(conversations, key = { inbox -> inbox.id }) { inbox ->
            ConversationCell(conversation = inbox) {
                navController.navigate(Screen.Messages.withId(inbox.id))
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.LightGray,
                thickness = 1.dp
            )
        }
    }
}

@Composable
fun ConversationCell(conversation: Conversation, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = conversation.name,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = conversation.lastUpdated,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ConversationsListPreview() {
    val list = listOf(
        Conversation(
            id = "1",
            name = "Name 1",
            lastUpdated = "30 Nov 2024"
        ),
        Conversation(
            id = "2",
            name = "Name 2",
            lastUpdated = "30 Nov 2024"
        ),
        Conversation(
            id = "3",
            name = "Name 2",
            lastUpdated = "30 Nov 2024"
        )
    )

    ConversationsList(conversations = list, navController = rememberNavController())
}