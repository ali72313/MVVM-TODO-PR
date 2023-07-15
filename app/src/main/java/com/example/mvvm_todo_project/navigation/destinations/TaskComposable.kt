package com.example.mvvm_todo_project.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mvvm_todo_project.data.utils.Action
import com.example.mvvm_todo_project.data.utils.Constants
import com.example.mvvm_todo_project.ui.screens.task.TaskScreen
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Action) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = Constants.TASK_SCREEN,
        arguments = listOf(navArgument(Constants.TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(Constants.TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask)
        {
            sharedViewModel.updateTaskField(selectedTask = selectedTask)
        }


        TaskScreen(navigateToListScreen = navigateToListScreen, selectedTask, sharedViewModel)
    }
}