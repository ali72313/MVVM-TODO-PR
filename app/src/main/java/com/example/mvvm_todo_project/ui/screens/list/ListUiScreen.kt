package com.example.mvvm_todo_project.ui.screens.list

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.mvvm_todo_project.ui.theme.fabBackGroundColor
import com.example.mvvm_todo_project.ui.viewModels.SharedViewModel


@Composable
fun ListUiScreen(navigateToTaskScreen: (Int) -> Unit, sharedViewModel: SharedViewModel) {

    /*LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
        Log.d("ListScreen", "LaunchEffect Triggered")
    }*/

    val action by sharedViewModel.action

    val allTasks = sharedViewModel.allTask.collectAsState()
    // val allTasks by  sharedViewModel.allTask.collectAsState() these two lines do the same thing , but when we use by we dont need to use  .value

    //val searchAppbarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchAppbarState = sharedViewModel.searchAppBarState.value //this and the line above do the same thing
    val searchTextState: String by sharedViewModel.searchTextState


    sharedViewModel.handleDataBaseActions(action)
    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppbarState,
                searchTextState = searchTextState
            )
        },
        floatingActionButton = {
            ListFab(navigateToTaskScreen)
        }

    ) {
        Surface(modifier = Modifier.padding(it)) {
            ListContent(allTasks.value, navigateToTaskScreen)
        }
    }
}

@Composable
fun ListFab(navigateToTaskScreen: (Int) -> Unit) {

    FloatingActionButton(containerColor = fabBackGroundColor,
        onClick = { navigateToTaskScreen(-1) }
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = null)
    }

}


