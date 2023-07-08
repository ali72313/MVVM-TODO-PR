package com.example.mvvm_todo_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mvvm_todo_project.navigation.SetupNavigation
import com.example.mvvm_todo_project.ui.theme.MVVMTODOprojectTheme
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMTODOprojectTheme {
                val sharedViewModel: SharedViewModel = viewModel()
                navController = rememberNavController()
                SetupNavigation(navController = navController, sharedViewModel)
            }
        }
    }
}

