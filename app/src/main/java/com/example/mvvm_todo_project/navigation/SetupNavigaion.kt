package com.example.mvvm_todo_project.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mvvm_todo_project.data.utils.Constants
import com.example.mvvm_todo_project.data.utils.Constants.LIST_SCREEN
import com.example.mvvm_todo_project.navigation.destinations.listComposable
import com.example.mvvm_todo_project.navigation.destinations.taskComposable

@Composable
fun SetupNavigation(
    navController: NavHostController
) {
    val screen = rememberSaveable(navController) {
        Screens(navController = navController)
    }

    NavHost(navController = navController, startDestination = LIST_SCREEN) {
        listComposable { screen.task }

        taskComposable { screen.list }
    }
}


