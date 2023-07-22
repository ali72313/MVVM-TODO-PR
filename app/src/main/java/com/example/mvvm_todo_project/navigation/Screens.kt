package com.example.mvvm_todo_project.navigation


import androidx.navigation.NavHostController
import com.example.mvvm_todo_project.data.utils.Action
import com.example.mvvm_todo_project.data.utils.Constants.SPLASH_SCREEN
import com.example.mvvm_todo_project.data.utils.Constants.LIST_SCREEN


class Screens(navController: NavHostController) {

    val splashToList: () -> Unit = {
        navController.navigate(route = "list/${Action.NO_ACTION}") {
            popUpTo(SPLASH_SCREEN) { inclusive = true }
        }
    }

    val taskToList: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}")
        {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
    val listToTask: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}