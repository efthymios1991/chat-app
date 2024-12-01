package com.interview.chatapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.interview.chatapp.ui.conversations.view.ConversationsScreen
import com.interview.chatapp.ui.messages.view.MessagesScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Conversations.route
    ) {
        composable(
            route= Screen.Conversations.route
        ) {
            ConversationsScreen(navController = navController)
        }
        composable(
            route= Screen.Messages.route,
            arguments = listOf(navArgument(MESSAGES_SCREEN_ARGUMENT_ID) {
                type = NavType.StringType
            })
        ) {
            MessagesScreen(navController = navController)
        }
    }
}