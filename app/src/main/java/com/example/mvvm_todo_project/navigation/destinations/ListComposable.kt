package com.example.mvvm_todo_project.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mvvm_todo_project.data.utils.Constants.LIST_SCREEN
import com.example.mvvm_todo_project.data.utils.Constants.LIST_ARGUMENT_KEY
import com.example.mvvm_todo_project.ui.screens.list.ListScreen


fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (Int) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        ListScreen(navigateToTaskScreen = navigateToTaskScreen)
    }
}