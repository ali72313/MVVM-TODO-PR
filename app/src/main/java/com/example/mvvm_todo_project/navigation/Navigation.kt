package com.example.mvvm_todo_project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.mvvm_todo_project.data.utils.Constants.SPLASH_SCREEN
import com.example.mvvm_todo_project.navigation.destinations.listComposable
import com.example.mvvm_todo_project.navigation.destinations.splashComposable
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
    val screen1 = Screens(navController = navController)

    NavHost(navController = navController, startDestination = SPLASH_SCREEN) {

        splashComposable(navigateToListScreen = screen1.splashToList)
        listComposable(sharedViewModel = sharedViewModel, navigateToTaskScreen = screen1.listToTask)

        taskComposable(navigateToListScreen = screen1.taskToList, sharedViewModel = sharedViewModel)
    }
}



