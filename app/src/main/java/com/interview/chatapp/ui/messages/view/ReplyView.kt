package com.interview.chatapp.ui.messages.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReplyView(
    onSendClick: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text(text = "Type your reply...") }
        )

        Button(
            onClick = {
                if (text.isNotBlank()) {
                    onSendClick(text)
                    text = ""
                }
            }
        ) {
            Text(text = "Send")
        }
    }
}
