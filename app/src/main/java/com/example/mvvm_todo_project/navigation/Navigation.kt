package com.example.mvvm_todo_project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mvvm_todo_project.data.utils.Constants.LIST_SCREEN
import com.example.mvvm_todo_project.navigation.destinations.listComposable
import com.example.mvvm_todo_project.navigation.destinations.taskComposable
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable(sharedViewModel = sharedViewModel, navigateToTaskScreen = screen.task)

        taskComposable(navigateToListScreen = screen.list, sharedViewModel = sharedViewModel)
    }
}



