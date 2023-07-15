package com.example.mvvm_todo_project.navigation.destinations

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mvvm_todo_project.data.utils.Constants
import com.example.mvvm_todo_project.data.utils.Constants.LIST_SCREEN
import com.example.mvvm_todo_project.data.utils.Constants.LIST_ARGUMENT_KEY
import com.example.mvvm_todo_project.data.utils.toAction
import com.example.mvvm_todo_project.ui.screens.list.ListUiScreen
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel


fun NavGraphBuilder.listComposable(
    sharedViewModel: SharedViewModel,
    navigateToTaskScreen: (Int) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(Constants.LIST_ARGUMENT_KEY).toAction()
        LaunchedEffect(key1 = action)
        {
            sharedViewModel.action.value = action
        }

        ListUiScreen(navigateToTaskScreen = navigateToTaskScreen, sharedViewModel = sharedViewModel)
    }
}