package com.interview.chatapp.ui.navigation

const val MESSAGES_SCREEN_ARGUMENT_ID = "id"

sealed class Screen(val route: String) {
    object Conversations: Screen(route = "conversations_screen")
    object Messages: Screen(route = "messages_screen/{$MESSAGES_SCREEN_ARGUMENT_ID}") {
        fun withId(id: String): String {
            return this.route.replace("{$MESSAGES_SCREEN_ARGUMENT_ID}", id)
        }
    }
}