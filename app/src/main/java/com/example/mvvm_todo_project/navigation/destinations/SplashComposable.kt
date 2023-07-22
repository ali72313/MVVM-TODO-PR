package com.example.mvvm_todo_project.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mvvm_todo_project.data.utils.Constants
import com.example.mvvm_todo_project.data.utils.toAction
import com.example.mvvm_todo_project.ui.screens.list.ListUiScreen
import com.example.mvvm_todo_project.ui.screens.splash.SplashScreen
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel


fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
) {
    composable(
        route = Constants.SPLASH_SCREEN
    ) {

        SplashScreen(navigateToListScreen)
    }
}

