package com.example.mvvm_todo_project.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mvvm_todo_project.data.utils.Action
import com.example.mvvm_todo_project.data.utils.Constants

class Screens(navController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}")
        {
            popUpTo(Constants.LIST_SCREEN) { inclusive = true }
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate("task/$taskId")
    }
}